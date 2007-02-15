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

package com.cameocontrol.cameo.output;

import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeSet;

import com.cameocontrol.cameo.control.ChannelSet;
import com.cameocontrol.cameo.control.ConsolePrefrences;
import com.cameocontrol.cameo.control.ConsoleCue;
import com.cameocontrol.cameo.control.CameoCue;
import com.cameocontrol.cameo.control.ConsoleTimeing;

import dmx.DMX;
import dmx.DMXStub;
import dmx.StraightDMX;

public class CameoOutputManager implements OutputManager {
	private LiveCue _live;
	private Patch _patch;
	private DMX _output;
	private Timer _outputRefresher;
	
	public CameoOutputManager(ConsolePrefrences prefs, CameoPatch patch) {
		_live = new LiveCue(patch.getTotalChannels());
		_patch = patch;
		
		/**
		 * TODO fix me rob
		 * uncomment line below when the StraightDMX lib is setup for linux
		 */
		_output = new DMXStub(prefs.getStartCode());//new StraightDMX(prefs.getStartCode());
		_output.open(patch.getTotalDimmers(), 20);
		
		_outputRefresher = new Timer();
		_outputRefresher.schedule(new UpdateFromOutput(), 0, prefs.getOutputRefesh());
	}
	
	private class UpdateFromOutput extends TimerTask {
		public void run() {
			updateLive();
		}
	}
	
	public Patch getPatch() {return _patch;}
	
	public LiveCue getLiveCue() {return _live;}
	
	public ConsoleCue getLiveSnapShot() {
		ConsoleCue cue = new CameoCue();
		for(int x=0; x<_live._totalChannels; x++)
			cue.setLevel(x, _live.getLevel(x));
		
		return cue;
	}
	
	public void reSetOutput(ConsolePrefrences prefs){
		//_outControl.setLiveCue(new LiveCue(_show._settings._totalChannels));
		_output = new DMXStub(prefs.getStartCode());
		//_dmxOutput = new StraightDMX(_pref._startCode);
		_output.open(_patch.getTotalDimmers(), 20);
		_live.setTotalChannels(_patch.getTotalChannels());
		
		
		_outputRefresher = new Timer();
		_outputRefresher.schedule(new UpdateFromOutput(), 0, prefs.getOutputRefesh());
	}
	
	public boolean isSimulation() {return _output.isSimulation();}
	
	//Update the total number of channels in the live cue
	public void setTotalChannels(int Channels){
		_live.setTotalChannels(Channels);
		_patch.setTotalChannels(Channels);
	}
	
	public void setTotalDimmers(int dimmers){
		_patch.setTotalDimmers(dimmers);
		//TODO: try to reconnect to DMX device with less dimmers
	}
	
	public void startCode(short s) {
		//_startCode = s;
		//TODO restart DMX connection
	}
	
	public void refreshRate(int r) {
		_outputRefresher.cancel();
		_outputRefresher = new Timer();
		_outputRefresher.schedule(new UpdateFromOutput(), 0, r);
	}
	
	public void load(ConsoleCue c, ConsoleTimeing t){
		_live.clearMarks();
		//System.out.println(t.getDelayUpTime()+"/"+t.getDelayDownTime());
		//printLive(c.getLevels(0,_patch.getTotalChannels()));
		//printLive(_patch.translateLevelsToDim(c.getLevels(0,_patch.getTotalChannels())));
		short[] levels = _patch.translateLevelsToDim(c);
		_output.partialLoad(levels, t.getUpTime(), t.getDownTime(), t.getDelayUpTime(), t.getDelayDownTime());	
	}
	
	public void setLevelOut(ChannelSet c){
		TreeSet<Integer> dims;
		for(Integer i : c) {
			_live.setOut(i.intValue());
			dims = _patch.getDimsFor(i.intValue());
			for(Integer dim : dims)
				_output.setSingle(dim, (short)0);
		}
		_live.select(c);
	}
	
	public void plus(ChannelSet c, short l){
		TreeSet<Integer> dims;
		for(Integer i : c) {
			int chan = i.intValue();
			short lev = _live.plus(chan,l, !c.isSingle());
			dims = _patch.getDimsFor(chan);
			for(Integer dim : dims)
				_output.setSingle(dim, lev);
		}
		_live.select(c);
	}
	
	public void minus(ChannelSet c, short l){
		TreeSet<Integer> dims;
		for(Integer i : c) {
			int chan = i.intValue();
			short lev = _live.minus(chan,l);
			//System.err.println(lev+" "+_live.getLevel(chan));
			dims = _patch.getDimsFor(chan);
			for(Integer dim : dims)
				_output.setSingle(dim, lev);
		}
		_live.select(c);
	}
		
	public void setLevel(ChannelSet c, short l){
		TreeSet<Integer> dims;
		for(Integer i : c){
			_live.setLevel(i.intValue(),l);
			dims = _patch.getDimsFor(i.intValue());
			for(Integer dim : dims)
				_output.setSingle(dim, l);
		}
		_live.select(c);
	}

	public void setLevel(ChannelSet c, short[] l){
		TreeSet<Integer> dims;
		for(Integer i : c) {
			_live.setLevel(i.intValue(),l);
			dims = _patch.getDimsFor(i.intValue());
			for(Integer dim : dims)
				_output.setSingle(dim, _live.getLevel(i.intValue()));
		//_live.setLevel(start, end, l);
		}
		_live.select(c);
	}
		
	public void updateLive() {
		ConsoleCue c = _patch.translateLevelsToChan(_output.getCurrentFade());
		c.setDiscription("on the output");
		
		_live.updateCue(c);
		
	}
	
	private void printLive(short[] arr){
		boolean first = true;
		System.err.print("[");
		for(int x=0; x < arr.length; x++)
			if(first){
				System.err.print(arr[x]);
				first = false;
			}
			else
				System.err.print(","+arr[x]);
			
		System.err.println("]");
	}

	public void dimAt(int d, short l) {
		_output.setSingle(d,l);
		updateLive();
	}

	public void patchChan(int c, int d) {_patch.patch(c,d); }
	public void unpatchChan(int c){_patch.unpatchChan(c); }
	public void unpatchDim(int d) {_patch.unpatchDim(d); }
	public void patchClear() {_patch.unpatchAll();}
	
	public void patch1to1() {_patch.setPatch1to1();}
}
