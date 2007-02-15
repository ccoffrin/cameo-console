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

package com.cameocontrol.cameo.action;

import com.cameocontrol.cameo.control.ChannelSet;
import com.cameocontrol.cameo.control.SemInquery;
import com.cameocontrol.cameo.dataStructure.CommandLineMessage;
import com.cameocontrol.cameo.dataStructure.ErrorMessage;
import com.cameocontrol.cameo.dataStructure.FeedbackMessage;


public class SemVisitor implements IVisitor {
	private CommandLineMessage _errMsg;
	private String _consoleInput;
	private SemInquery _console;
	private ChanCollector _chans;
	
	public SemVisitor(SemInquery c) {
		_console = c;
		_errMsg = null;
		_chans = new ChanCollector();
	}
	
	public void doIt(ACTAction n) {
		n.visit(this);
	}
	
	public CommandLineMessage check(ACTAction n) {
		_consoleInput = "";
		_errMsg = null;
		doIt(n);
		return _errMsg;
	}
	
	public CommandLineMessage check(ACTAction n, String s) {
		_consoleInput = s;
		_errMsg = null;
		doIt(n);
		return _errMsg;
	}
	
	private boolean checkChannels(ChannelSet cs) {
		return true;
		
	}

	public void applyACTChanSelect(ACTChanSelect n) {
		ChannelSet cs = _chans.collect(n._channels);
		if(checkChannels(cs))
			n._channels.setChannels(cs);
	}
	
	public void applyACTChanAtLevel(ACTAtLevel n) {
		if(n.hasSelection()) {
			ChannelSet cs = _chans.collect(n._channels);
			if(checkChannels(cs))
				n._channels.setChannels(cs);
		}
		/*
			_console.at(int2short(n._level));
		else
			_console.at(n._channels.getChannels(), int2short(n._level));
		*/
	}

	public void applyACTChanAtCue(ACTAtCue n) {
		if(n.hasSelection()) {
			ChannelSet cs = _chans.collect(n._channels);
			if(checkChannels(cs))
				n._channels.setChannels(cs);
		}
		/*
		if(!n.hasSelection())
			_console.at(int2short(n._level));
		else
			_console.at(n._channels.getChannels(), n._level);
			*/
	}
	
	public void applyACTOut(ACTOut n) {
		if(n.hasSelection()) {
			ChannelSet cs = _chans.collect(n._channels);
			if(checkChannels(cs))
				n._channels.setChannels(cs);
		}
		/*
		if(!n.hasSelection())
			_console.out();
		else
			_console.out(n._channels.getChannels());
			*/
	}

	public void applyACTPlus(ACTPlus n) {
		if(n.hasSelection()) {
			ChannelSet cs = _chans.collect(n._channels);
			if(checkChannels(cs))
				n._channels.setChannels(cs);
		}
		/*
		if(!n.hasSelection())
			_console.plus(int2short(n._level));
		else
			_console.plus(n._channels.getChannels(), int2short(n._level));
			*/
	}

	public void applyACTMinus(ACTMinus n) {
		if(n.hasSelection()) {
			ChannelSet cs = _chans.collect(n._channels);
			if(checkChannels(cs))
				n._channels.setChannels(cs);
		}
		/*
		if(!n.hasSelection())
			_console.minus(int2short(n._level));
		else
			_console.minus(n._channels.getChannels(), int2short(n._level));
			*/
	}

	public void applyACTBack(ACTBack n) {
		// TODO Auto-generated method stub
		
	}

	public void applyACTDeleteCue(ACTDeleteCue n) {
		if(!n._conferm && _console.hasCue(n._cue._number)) {
			_errMsg = new FeedbackMessage(_consoleInput +" confirm");
		}
		else {
			//TODO check some other stuff.
		}
	}

	public void applyACTGo(ACTGo n) {
		// TODO Auto-generated method stub
		
	}

	public void applyACTGotoCue(ACTGotoCue n) {
		// TODO Auto-generated method stub
		
	}

	public void applyACTLoadCue(ACTLoadCue n) {
		// TODO Auto-generated method stub
		
	}

	public void applyACTRecordCue(ACTRecordCue n) {
		if(!n._conferm && _console.hasCue(n._cue._number)) {
			_errMsg = new FeedbackMessage(_consoleInput +" confirm");
		}
		else {
			//TODO check some other stuff.
		}
			
	}

	public void applyACTDimAtLevel(ACTDimAtLevel n) {
		// TODO Auto-generated method stub
		
	}

	public void applyACTChannelSelection(ACTChanSelection n) {
		// TODO Auto-generated method stub
		
	}

	public void applyACTCue(ACTCue n) {
		// TODO Auto-generated method stub
		
	}

	public void applyACTCueMod(ACTCueMod n) {
		// TODO Auto-generated method stub
		
	}
	
	public void applyACTCueMove(ACTCueMove n) {
		// TODO Auto-generated method stub
		
	}

	public void applyACTLevelMod(ACTLevelMod n) {}

	public void applyACTChannel(ACTChannel n) {
		// TODO Auto-generated method stub
		
	}

	public void applyACTChannelBinOp(ACTChanBinOp n) {
		// TODO Auto-generated method stub
		
	}

	public void applyACTChanThru(ACTChanThru n) {
		// TODO Auto-generated method stub
		
	}

	public void applyACTChanExcept(ACTChanExcept n) {
		// TODO Auto-generated method stub
		
	}

	public void applyACTChanAnd(ACTChanAnd n) {
		// TODO Auto-generated method stub
		
	}
	
	public void applyACTSettingsShowGenralMod(ACTSettingsShowGenralMod n) {
		// TODO Auto-generated method stub
		
	}

	public void applyACTSettingsShowChannelMod(ACTSettingsShowChannelMod n) {
		// TODO Auto-generated method stub
		
	}

	public void applyACTSettingsPrefMod(ACTSettingsPrefMod n) {
		// TODO Auto-generated method stub
		
	}

	public void applyACTPatchChan(ACTPatchChan n) {
		n._channel--;
		n._dimmer--;
	}

	public void applyACTUnpatchDim(ACTUnpatchDim n) {
		n._dimmer--;
		
	}

	public void applyACTUnpatchChan(ACTUnpatchChan n) {
		n._channel--;
	}

	public void applyACTPatch1to1(ACTPatch1to1 n) {}

	public void applyACTNoOp(ACTNoOp n) {}

	public void applyACTNext(ACTNext n) {
		if(!_console.getSelection().isSingle())
			_errMsg = new ErrorMessage("Current selection is not a single channel");
		else {
			n._currentChan = _console.getSelection().getSingle();
			n._level = _console.getLiveCue().getLevel(n._currentChan);
			n._nextChan = getNextChan(n._currentChan);
		}
		
	}

	public void applyACTPrevious(ACTPrevious n) {
		if(!_console.getSelection().isSingle())
			_errMsg = new ErrorMessage("Current selection is not a single channel");
		else {
			n._currentChan = _console.getSelection().getSingle();
			n._level = _console.getLiveCue().getLevel(n._currentChan);
			n._nextChan = getPrevChan(n._currentChan);
		}
	}
	
	private int getNextChan(int n) {
		int next = n+1;
		while ((!_console.isPatchedChan(next)) && (next != n)){
			if(next >= _console.getTotalChannels())
				next=0;
			else
				next++;
		}
		return next;
	}
	
	private int getPrevChan(int n) {
		int prev = n-1;
		while (!_console.isPatchedChan(prev) && (prev != n)){
			if(prev < 0)
				prev=_console.getTotalChannels()-1;
			else
				prev--;
		}
		return prev;
	}

	public void applyACTPatchClear(ACTPatchClear n) {}
	public void applyACTCuesClear(ACTCuesClear n) {}

	public void applyACTSaveShow(ACTSaveShow n) {
		//Checking done in UI
	}

	public void applyACTLoadShow(ACTLoadShow n) {
		//Check done in UI
	}

	public void applyACTChanLocation(ACTChanLocation n) {
		// TODO check to make sure channel is in range	
	}
}
