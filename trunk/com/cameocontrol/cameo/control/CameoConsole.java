/************************************************************************************
 *  Copyright 2006 Carleton Coffrin
 * 
 *  This file is part of Cameo.
 *  
 *  Cameo is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *  
 *  Cameo is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with Cameo; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *  
 *************************************************************************************/

package com.cameocontrol.cameo.control;

import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;

import com.cameocontrol.cameo.control.ConsoleSettings.RecordMode;
import com.cameocontrol.cameo.output.LiveCue;
import com.cameocontrol.cameo.output.CameoOutputManager;
import com.cameocontrol.cameo.output.Patch;
import com.cameocontrol.cameo.resource.ResourceManager;

public class CameoConsole implements Console, SemInquery{
	private CameoPrefrences _pref;
	private CameoShow _show;
	
	private CameoOutputManager _outControl;
	private ResourceManager _resourceUpdater;

	public CameoConsole(CameoPrefrences cp, CameoOutputManager oc, ResourceManager ru, CameoShow cs) {

		_show = cs;
		_pref = cp;
		_outControl = oc;
		_resourceUpdater = ru;
	}

	//Console Options
	public ConsoleSettings getSettings() {return _show._settings;}
	public ConsolePrefrences getPrefrences() {return _pref;}
	
	//Cue List methods
	public BasicCueList cueList() {return _show._cuelist;}
	public Patch patch() {return _outControl.getPatch();}
	public LiveCue liveCue() {return _outControl.getLiveCue();}
	public CameoMagicSheet getMagicSheet() {return _show._magic;}
	
	public CameoCue makeCue() {return new CameoCue();}
	public void addCue(ConsoleFade c) { if(_show._settings.isTracking()) _show._cuelist.recordTracking(c); else _show._cuelist.addCueOnly(c);}
	public void removeCue(ConsoleFade c) { if(_show._settings.isTracking()) _show._cuelist.removeTracking(c); else _show._cuelist.removeCueOnly(c);}
	
	public boolean isCurrentCue(CameoFade c) {return _show._cuelist.isCurrentCue(c);}
	public ConsoleFade getCurrentCue() {return _show._cuelist.getCurrentCue();}
	public int getCurrentCueNumber() {return _show._cuelist.getCurrentCue().getNumber();}
	public void setCurrentCue(CameoFade c) {_show._cuelist.setCurrentCue(c);}
	
	public ConsoleFade getCue(int x) {return _show._cuelist.getCueNumbered(x);}
	//public int getTotalCues() {return _show._cuelist.size();}
	//public CueTransition getCue(int x) {return _show._cuelist.getCueIndexed(x);}
	
	public int getNextCueNumber() {return _show._cuelist.getNextCueNumber();}
	public ConsoleFade getNextCue() {return _show._cuelist.getNextCue();}
	
	//public void updateLive() {_outControl.updateLive();}
	//Patch Methods
	//public boolean isPatched(int x) {return _show._patch.isPatched(x);}
	

	
	/*********************
	 * BOARD Settings Update
	 */
	
	
	public void recordMode(RecordMode r) {_show._settings._mode = r;}

	public void totalChannels(int c) {
		_outControl.setTotalChannels(c);
		//_show._cuelist.setTotalChannels(c);
		
		_show._settings._totalChannels = c;
	}

	public void totalDimmers(int d) {
		_outControl.setTotalDimmers(d);
		_show._settings._totalDimmers = d;
	}
	public void defaultUpTime(int t) {_show._settings._upTime = t;}
	public void defaultDownTime(int t) {_show._settings._downTime = t;}
	public void defaultGotoCueTime(int t) {_show._settings._gotoCueTime = t;}
	public void showTitle(String s) {_show._settings._showTitle = s; }//_updateUI.updateShowInfo();}
	public void showComment(String s) {_show._settings._showComment = s; }//_updateUI.updateShowInfo();}

	public void channelsPerLine(int t) {
		_show._settings._ChannelsPerLine = t;
	}

	public void channelsHGroup(int t) {
		_show._settings._ChannelGrouping = t;
	}

	public void channelsVGroup(int t) {
		_show._settings._LineGrouping = t;
	}

	public void refreshRate(int r) {
		_outControl.refreshRate(r);
		_pref._refreshOutputRate = r;
	}

	public void startCode(short s) {
		_outControl.startCode(s);
		_pref._startCode = s;
	}

	public void middleMouseAction(String s) {
		_pref._mouseMiddleAction = s;
	}


	/*********************
	 * BOARD Control from the user's stand point
	 */
	
	private void go(ConsoleTimeing t) {
		_show._cuelist.setCurrentCue(_show._cuelist.getNextCue());
		_outControl.load(_show._cuelist.getCurrentCue().getCue(), t);
		//TODO if there is a follow time, another process should be made to count down that time
	}
	
	public void go() {
		ConsoleFade cue = _show._cuelist.getNextCue();
		go(cue.getTimeing());
	}
	
	public void back() {
		//_show._cuelist.setCurrentCue(_show._cuelist.getPrevCue());
		_show._cuelist.setCurrentCue(_show._cuelist.getPrevCue());
		_outControl.load(_show._cuelist.getCurrentCue().getCue(), new CameoTime(_show._settings._gotoCueTime, _show._settings._gotoCueTime, 0, 0));
	}
	
	public void loadCue(int cueNum) {
		_show._cuelist.setCurrentCue(_show._cuelist.getCueNumbered(cueNum));
	}
	
	public void gotoCue(int cueNum, ConsoleTimeing t) {
		/*
		_show._cuelist.setCurrentCue(_show._cuelist.getCueNumbered(cueNum));
		_show._cuelist.setCurrentCue(_show._cuelist.getPrevCue());
		go(t);
		*/
		
		_show._cuelist.setCurrentCue(_show._cuelist.getCueNumbered(cueNum));
		_outControl.load(_show._cuelist.getCurrentCue().getCue(), t);
	}
	
	public void gotoCue(int cueNum) {
		gotoCue(cueNum, new CameoTime(_show._settings._gotoCueTime, _show._settings._gotoCueTime, 0, 0));
	}
	
	public void select(ChannelSet c) {_outControl.getLiveCue().select(c);}
	public void out(ChannelSet c) {_outControl.setLevelOut(c);}
	public void plus(ChannelSet c, short l) {_outControl.plus(c,l);}
	public void minus(ChannelSet c, short l) {_outControl.minus(c,l);}
	public void at(ChannelSet c, short l) {_outControl.setLevel(c,l);}
	public void at(ChannelSet c, int cueNum) {
		_outControl.setLevel(c,_show._cuelist.getCueNumbered(cueNum).getCue().getLevels(c.min(), c.max()));
	}
	
	public void at(short l) {at(_outControl.getLiveCue().getCurrentSelection(), l);}
	public void plus(short l) {plus(_outControl.getLiveCue().getCurrentSelection(), l);}
	public void minus(short l) {minus(_outControl.getLiveCue().getCurrentSelection(), l);}
	public void at(int cueNum) {at(_outControl.getLiveCue().getCurrentSelection(), cueNum);}
	public void out() {out(_outControl.getLiveCue().getCurrentSelection());}
	
	
	
	
	public void recordCue(int cueNum, ConsoleTimeing t, int nextCue) {
		CameoFade temp;
		if(nextCue < 0)
			temp = new CameoFade(cueNum, t, _outControl.getLiveSnapShot());
		else {
			temp = new CameoFade(cueNum, t, _outControl.getLiveSnapShot());
			temp.setNextCue(_show._cuelist.getCueNumbered(nextCue));
		}
		
		addCue(temp);
	}
	
	
	
	public void recordCue(int cueNum, int NextNum) {
		recordCue(cueNum, new CameoTime(_show._settings._upTime, _show._settings._downTime, 0, 0), NextNum);
	}
	
	public void recordCue(int cueNum) {
		recordCue(cueNum, new CameoTime(_show._settings._upTime, _show._settings._downTime, 0, 0), -1);
	}
	
	public void recordCue() {
		ConsoleFade cue = _show._cuelist.getCurrentCue();
		//TODO introduces a bug that will remove follow number when record used
		recordCue(cue.getNumber(), cue.getTimeing(), -1);
	}
	
	public void deleteCue(int cueNum) {
		removeCue(_show._cuelist.getCueNumbered(cueNum));
	}
	
	public void deleteAllCues() {_show._cuelist.clearCues();}

	
	public void time(int cueNum, int ut, int dt) {
		_show._cuelist.getCueNumbered(cueNum).getTimeing().setTime(ut,dt);
	}
	public void time(int ut, int dt) {
		//int cueNum = _show._cuelist.getCurrentCue().getNumber();
		time(_show._cuelist.getCurrentCue().getNumber(), ut, dt);}
	
	public void delay(int cueNum, int ut, int dt) {
		_show._cuelist.getCueNumbered(cueNum).getTimeing().setDelayTime(ut,dt);
	}
	public void delay(int ut, int dt) {delay(_show._cuelist.getCurrentCue().getNumber(), ut, dt);}
	
	public void follow(int cueNum, int t) {
		_show._cuelist.getCueNumbered(cueNum).getTimeing().setFollowTime(t);
	}
	public void follow(int t) {follow(_show._cuelist.getCurrentCue().getNumber(), t);}
	
	public void link(int cueNum, int nextCue) {
		_show._cuelist.getCueNumbered(cueNum).setNextCue(_show._cuelist.getCueNumbered(nextCue));
	}
	public void link(int nextCue) {link(_show._cuelist.getCurrentCue().getNumber(), nextCue);}
	

	public void description(int cueNum, String d) {
		_show._cuelist.getCueNumbered(cueNum).getCue().setDiscription(d);
	}
	public void description(String d) {description(_show._cuelist.getCurrentCue().getNumber(), d);}
	
	public void copyCue(int from, int to) {
		ConsoleFade cue = _show._cuelist.getCueNumbered(from);
		removeCue(cue);
		cue = new CameoFade(to, cue.getTimeing(), cue.getCue());
		addCue(cue);	
	}
	

	/*********************
	 * Semantic Inquery for testing input correctness
	 */
	public ChannelSet getSelection() {return _outControl.getLiveCue().getCurrentSelection();}
	
	
	
	/*********************
	 * BOARD Inquery for testing input correctness
	 */
	public boolean hasCue(int cueNum) {
		if (_show._cuelist.getCueNumbered(cueNum) == null)
			return false;
		return true;
	}
	
	public boolean hasChannel(int chanNum) {
		if(chanNum >= _show._settings._totalChannels)
			return false;
		return true;
	}
	
	public int getTotalChannels() {return _show._settings._totalChannels;}

	public Collection<Integer> getDimmers(int c) {
		return _outControl.getPatch().getDimsFor(c);
	}

	public Collection<Integer> getUnpachedDims() {
		return _outControl.getPatch().getUnpachedDims();
	}

	public int getTotalCues() {return _show._cuelist.size();}

	public boolean isCurrentCue(int x) {return _show._cuelist.isCurrentCue(_show._cuelist.getCueNumbered(x));}

	public LiveCue getLiveCue() {return _outControl.getLiveCue();}
	public boolean isPatchedChan(int c) {return _outControl.getPatch().isChanPatched(c);}

	public boolean isSimulation() {return _outControl.isSimulation();}	
	
	public Iterator<ConsoleFade> getCues() {return _show._cuelist.iterator();}
	
	
	/*********************
	 * Magic Sheet Manipulation
	 */
	public void channelLocation(int c, double x, double y) {
		_show._magic.AddChannel(c,x,y);
	}

	
}
