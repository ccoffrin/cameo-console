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
import com.cameocontrol.cameo.resource.Resource;

public interface ConsolePrefrences extends Resource<PrefrencesData> {
	
	public String START_CODE_TAG = "StartCode";
	public String OUTOUT_REFRESH_TAG = "RefreshRate";
	public String MOUSE_MIDDLE_ACTION_TAG = "MouseMiddleAction";
	public String SHOW_PATH_TAG = "showPath";
	public String LAST_SAVED_FILE_NAME_TAG = "lastShow";

	public short getStartCode();
	public String getMouseMiddleAction();
	public int getOutputRefesh();
	public String getShowPath();
	public String getLastSavedName();
}
