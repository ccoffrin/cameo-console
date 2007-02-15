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

public class CameoSettings extends BasicSettings {
	public String getIDValue() {return "cameo";}
	
	public CameoSettings(){
		_mode = RecordMode.TRACKING;
		_totalChannels = 10;
		_totalDimmers = 512;
		_upTime = 4999;
		_downTime = 4999;
		_gotoCueTime = 1000;
		_ChannelsPerLine = 20;
		_ChannelGrouping = 5;
		_LineGrouping = 5;
		_showTitle = "";
		_showComment = "";
	}
	
	/*	
	public CameoSettings(int x){
		this();
		_totalChannels = x;
	}
	
	public CameoSettings(int x, RecordMode mode){
		this();
		_mode = mode;
		_totalChannels = x;
	}
	*/
	
}
