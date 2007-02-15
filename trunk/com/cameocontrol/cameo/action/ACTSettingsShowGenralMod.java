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

import com.cameocontrol.cameo.control.ConsoleSettings.RecordMode;

public class ACTSettingsShowGenralMod extends ACTAction{

	RecordMode _recordMode;
	int _channels;
	int _dimmers;
	int _upTime;
	int _downTime;
	int _gotoCueTime;
	String _title;
	String _comment;
	
	public ACTSettingsShowGenralMod() {
		
		_recordMode = null;
		_channels = -1;
		_dimmers = -1;
		_upTime = -1;
		_downTime = -1;
		_gotoCueTime = -1;
		_title = null;
		_comment = null;
	}

	public boolean recordModeUpdate() {return (_recordMode != null);}
	public boolean channelUpdate() {return (_channels >= 0);}
	public boolean dimmerUpdate() {return (_dimmers >= 0);}
	public boolean upTimeUpdate() {return (_upTime >= 0);}
	public boolean downTimeUpdate() {return (_downTime >= 0);}
	public boolean gotoCueTimeUpdate() {return (_gotoCueTime >= 0);}
	public boolean titleUpdate() {return (_title != null);}
	public boolean commentUpdate() {return (_comment != null);}


	public void setRecordMode(RecordMode x) {_recordMode = x;}
	public void setChannels(int x) {_channels = x;}
	public void setDimmers(int x) {_dimmers = x;}

	//public void setTime(int x) {_upTime = x; _downTime = x;}
	public void setUpTime(int x) {_upTime = x;}
	public void setDownTime(int x) {_downTime = x;}
	public void setGotoCueTime(int x) {_gotoCueTime = x;}
	
	public void setTitle(String s) {_title = s;}
	public void setComment(String s) {_comment = s;}
	
	
	@Override
	public void visit(IVisitor v) {
		v.applyACTSettingsShowGenralMod(this);
	}
}
