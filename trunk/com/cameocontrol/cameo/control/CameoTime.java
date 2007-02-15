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


public class CameoTime implements ConsoleTimeing {

	private int _upTime;
	private int _downTime;
	private int _delayUpTime;
	private int _delayDownTime;
	
	private int _followTime;
	
	public CameoTime() {
		_upTime = 0;
		_downTime = 0;
		_delayUpTime = 0;
		_delayDownTime = 0;
		
		_followTime = ConsoleTimeing.NoFollowTime;
	}
	
	public CameoTime(int ut, int dt, int udt, int ddt) {
		_upTime = ut;
		_downTime = dt;
		_delayUpTime = udt;
		_delayDownTime = ddt;
		
		_followTime = ConsoleTimeing.NoFollowTime;
	}
	
	public void setFollowTime(int t) {_followTime = t;}
	public int getFollowTime() {return _followTime;}
	
	public void setTime(int ut, int dt) {_upTime = ut; _downTime =  dt;}
	public void setDelayTime(int ut, int dt) {_delayUpTime = ut; _delayDownTime =  dt;}
	
	public int getDownTime() {return _downTime;}
	public int getUpTime() {return _upTime;}
	
	public int getDelayDownTime() {return _delayDownTime;}
	public int getDelayUpTime() {return _delayUpTime;}
	
	//public void setTime(int t) {upTime = t; downTime =  t;}
	//public void setDelayTime(int t) {delayUpTime = t; delayDownTime =  t;}
	
	public String timeToString()
	{
		if(_upTime == _downTime)
			return Float.toString(_upTime/(float)1000);
		return Float.toString(_upTime/(float)1000) + "/" + Float.toString(_downTime/(float)1000);
	}
	
	public String delayToString()
	{
		if(_delayUpTime == _delayDownTime)
			if(_delayUpTime == 0)
				return "";
			else
				return Float.toString(_delayUpTime/(float)1000);
		return Float.toString(_delayUpTime/(float)1000) + "/" + Float.toString(_delayDownTime/(float)1000);
	}
	
	public CameoTime clone(){
		CameoTime t = new CameoTime();
		t._upTime = _upTime;
		t._downTime = _downTime;
		t._delayUpTime = _delayUpTime;
		t._delayDownTime = _delayDownTime;
		
		t._followTime = _followTime;
		
		return t;
	}
}
