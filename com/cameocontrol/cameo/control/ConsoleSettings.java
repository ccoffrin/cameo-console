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

import com.cameocontrol.cameo.file.adt.SettingsData;
import com.cameocontrol.cameo.resource.Resource;

public interface ConsoleSettings extends Resource<SettingsData> {
	public enum RecordMode {TRACKING, CUE_ONLY}
	
	public String RECORD_MODE_TAG = "RecordMode";
	public String TOTAL_CHANNELS_TAG = "TotalChannels";
	public String TOTAL_DIMMERS_TAG = "TotalDimmers";
	public String DEFAULT_UP_TIME_TAG = "DefaultUpTime";
	public String DEFAULT_DOWN_TIME_TAG = "DefaultDownTime";
	public String DEFAULT_GOTOCUE_TIME_TAG = "DefaultGotoCueTime";
	public String SHOW_TITLE_TAG = "ShowTitle";
	public String SHOW_COMMENT_TAG = "ShowComment";
	public String CHANNELS_PER_LINE_TAG = "ChannelsPerLine";
	public String CHANNELS_PER_HORIZONTEL_GROUP_TAG = "ChannelHGroup";
	public String CHANNELS_PER_VERTICAL_GROUP_TAG = "ChannelVGroup";
	
	
	public String getTitle();
	public String getComment();
	
	public int getChannels();
	public int getDimmers();

	//TODO this should be moved into it's own channel layout properties node
	public int getChannelsPerLine();
	public int getChannelGrouping();
	public int getLineGrouping();
	
	public int getDefaultUpTime();
	public int getDefaultDownTime();
	public int getDefaultGotoCueTime();
	
	public RecordMode getRecordMode();
	public boolean isTracking();
	public boolean isQueOnly();
}