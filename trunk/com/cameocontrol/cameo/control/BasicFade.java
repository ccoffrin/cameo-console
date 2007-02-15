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

import com.cameocontrol.cameo.file.adt.FadeData;

public abstract class BasicFade implements ConsoleFade {
	protected int _number; //TODO cue number should probably move into the CueList... Maybe
	protected ConsoleTimeing _time;
	protected ConsoleFade _next; //only non null if jumping cue sequence
	protected int _nextNumber;
	protected ConsoleCue _cue;
	
	//private int totalSections = 26;
	
	/*secional cue ideas,
	 * 	a list of cue transitions each tranistion has a section name <---
	 * 	a list of section numberer one per channel
	 * 	
	*/
	//private char section = ' ';

	protected BasicFade(int n, ConsoleTimeing t, ConsoleCue cue){
		_number = n;
		_time = t;
		_cue = cue;
		_nextNumber = DEFAULT_NEXT;
		_next = null;
	}

	public int getNumber() {return _number;}
	
	public ConsoleTimeing getTimeing() {return _time;}
	
	public ConsoleCue getCue() {return _cue;}

	public void setNextCue(ConsoleFade c) {_next = c;}
	public ConsoleFade getNextCue () {return _next;}
	
	public void setNextCueNumber(int num) {_nextNumber = num;}
	public int getNextCueNumber() {return _nextNumber;}
	
	
	public int compareTo(ConsoleFade fade) {
		return _number - fade.getNumber();
	}
	
	
	public boolean equals(ConsoleFade fade) {
		return fade.getNumber() == _number;	
	}

	public String toString(){
		return _number+" "+_cue.getDiscription();
	}
	
	public FadeData distill() {
		FadeData fd = new FadeData(
				_number, 
				_time.getUpTime(), 
				_time.getDownTime(), 
				_time.getDelayUpTime(), 
				_time.getDelayDownTime(), 
				_cue.distill()
			);
		
		return fd;
	}

	public void extractFrom(FadeData data) {
		_number = data.getCueNumber();
		_time = new CameoTime(data.getUpTime(), data.getDownTime(), data.getDelayUpTime(), data.getDelayDownTime());
		_nextNumber = data.getNextCue();
		
		CameoCue cue = new CameoCue();
		cue.extractFrom(data.getCue());
		_cue = cue;
	}
}
