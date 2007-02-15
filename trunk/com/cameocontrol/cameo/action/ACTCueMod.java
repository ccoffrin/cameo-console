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

public class ACTCueMod extends ACTAction{
	int _number;
	int _upTime;
	int _downTime;
	int _upTimeDelay;
	int _downTimeDelay;
	
	int _nextCue;
	int _followTime;
	
	String _description;
	
	public ACTCueMod() {
		_number = -1;
		_upTime = -1;
		_downTime = -1;
		_upTimeDelay = -1;
		_downTimeDelay = -1;
		
		_nextCue = -1;
		_followTime = -1;
		_description = null;
	}
	
	public boolean cueNumber() {return (_number >= 0);}
	public boolean timeUpdate() {return (_upTime >= 0 || _downTime >= 0);}
	public boolean delayUpdate() {return (_upTimeDelay >= 0 || _downTimeDelay >= 0);}
	public boolean followUpdate() {return (_followTime >= 0);}
	public boolean linkUpdate() {return (_nextCue >= 0);}
	public boolean descriptionUpdate() {return (_description != null);}

	public void setCueNumber(int x) {_number = x;}
	public void setTime(int x) {_upTime = x; _downTime = x;}
	public void setUpTime(int x) {_upTime = x;}
	public void setDownTime(int x) {_downTime = x;}
	
	public void setDelayTime(int x) {_upTimeDelay = x; _downTimeDelay = x;}
	public void setDelayUpTime(int x) {_upTimeDelay = x;}
	public void setDelayDownTime(int x) {_downTimeDelay = x;}
	
	public void setNextCue(int x) {_nextCue = x;}
	
	public void setFollowTime(int x) {_followTime = x;}
	
	public void setDescription(String s) {_description = s;}
	
	@Override
	public void visit(IVisitor v) {
		v.applyACTCueMod(this);
	}

}
