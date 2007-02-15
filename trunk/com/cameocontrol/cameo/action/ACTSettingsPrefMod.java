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

public class ACTSettingsPrefMod extends ACTAction{
	
	String _middleMouseAction;
	int _refreshRate;
	int _startCode;
	
	public ACTSettingsPrefMod() {
		_middleMouseAction = null;
		_refreshRate = -1;
		_startCode = -1;
	}
	
	public boolean middleMouseActionUpdate() {return _middleMouseAction != null;}
	public boolean refreshRateUpdate() {return _refreshRate > 0;}
	public boolean startCodeUpdate() {return _startCode >= 0;}
	
	public void setMiddleMouseAction(String s) {_middleMouseAction = s;}
	public void setRefreshRate(int x) {_refreshRate = x;}
	public void setStartCode(int x) {_startCode = x;}
	
	@Override
	public void visit(IVisitor v) {
		v.applyACTSettingsPrefMod(this);
	}

}
