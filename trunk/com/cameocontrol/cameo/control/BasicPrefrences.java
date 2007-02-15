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

import com.cameocontrol.cameo.file.adt.PrefrencesData;
import com.cameocontrol.cameo.util.HashtableUtils;

public abstract class BasicPrefrences implements ConsolePrefrences {
	protected String _mouseMiddleAction;
	protected String _showPath;
	protected String _lastSavedFile;
	protected int _refreshOutputRate;
	protected short _startCode;
	
	public int getOutputRefesh() {return _refreshOutputRate;}
	public void setOutputRefesh(int or) {_refreshOutputRate = or;}
	
	public String getMouseMiddleAction() {return _mouseMiddleAction;}
	public void setMouseMiddleAction(String mma) {_mouseMiddleAction = mma;}

	public short getStartCode() {return _startCode;}
	public void setStartCode(short sc) {_startCode = sc;}
	
	public String getShowPath() {return _showPath;}
	public void setShowPath(String sp) {_showPath = sp;}

	public String getLastSavedName() {return _lastSavedFile;}
	public void setLastSavedName(String lsf) {_lastSavedFile = lsf;}
	
	public PrefrencesData distill(){
		PrefrencesData pd = new PrefrencesData();
		
		pd.put(ID_ATTRIB,getIDValue());
		pd.put(START_CODE_TAG, Short.toString(_startCode));
		pd.put(MOUSE_MIDDLE_ACTION_TAG, _mouseMiddleAction);
		pd.put(OUTOUT_REFRESH_TAG, Integer.toString(_refreshOutputRate));
		pd.put(SHOW_PATH_TAG, _showPath);
		pd.put(LAST_SAVED_FILE_NAME_TAG, _lastSavedFile);
		
		return pd;
	}
	
	public void extractFrom(PrefrencesData data) {
		if(data.containsKey(ID_ATTRIB))
			assert(data.get(ID_ATTRIB).equals(getIDValue()));
		
		if(data.containsKey(START_CODE_TAG))
			_startCode = Short.parseShort(data.get(START_CODE_TAG));
		
		_refreshOutputRate = HashtableUtils.checkInt(_refreshOutputRate, OUTOUT_REFRESH_TAG, data);
		
		_mouseMiddleAction = HashtableUtils.check(_mouseMiddleAction, MOUSE_MIDDLE_ACTION_TAG, data);
		_showPath = HashtableUtils.check(_showPath, SHOW_PATH_TAG, data);
		_lastSavedFile = HashtableUtils.check(_lastSavedFile, LAST_SAVED_FILE_NAME_TAG, data);
	}
	
}
