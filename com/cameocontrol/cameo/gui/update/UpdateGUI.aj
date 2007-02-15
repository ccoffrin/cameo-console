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

package com.cameocontrol.cameo.gui.update;

import com.cameocontrol.cameo.gui.CameoGUI;
import com.cameocontrol.cameo.gui.ChannelGUI;
import com.cameocontrol.cameo.gui.MagicSheetGUI;
import com.cameocontrol.cameo.gui.CueListGUI;
import com.cameocontrol.cameo.gui.SSheetGUI;
import com.cameocontrol.cameo.gui.ShowInfoGUI;

import com.cameocontrol.cameo.control.ConsoleTimeing;
import com.cameocontrol.cameo.output.LiveCue;


public aspect UpdateGUI {
	//private GUIUpdater _guiUpdater = new UpdateStub(); //init to stub if nessisary
	//private Update _initUpdater = new Update(); //init to stub if nessisary
	
	private Rebuildable _channels = new RebuildStub();
	private Rebuildable _magicSheet = new RebuildStub();
	private Updateable _cueList = new UpdateStub();
	private Updateable _spreadSheet = new UpdateStub();
	private Updateable _groupsList = new UpdateStub();
	private Updateable _showInfo = new UpdateStub();
	
	pointcut initGUI() : call(CameoGUI.new(..)); 
	//after() : initGUI() {_guiUpdater = _initUpdater;}
	
	
	//pointcut initGroupListGUI() : call(GroupListGUI.new(..));
	pointcut initChannelGUI() : call(ChannelGUI.new(..));
	pointcut initMagicSheetGUI() : call(MagicSheetGUI.new(..));
	pointcut initCueListGUI() : call(CueListGUI.new(..));
	pointcut initSpreadSheetGUI() : call(SSheetGUI.new(..));
	pointcut initShowInfoGUI() : call(ShowInfoGUI.new(..));
	
	after() returning (ChannelGUI gui) : initChannelGUI() {_channels = gui;}
	after() returning (MagicSheetGUI gui) : initMagicSheetGUI() {_magicSheet = gui;}
	after() returning (CueListGUI gui) : initCueListGUI() {_cueList = gui;}
	after() returning (SSheetGUI gui) : initSpreadSheetGUI() {_spreadSheet = gui;}
	after() returning (ShowInfoGUI gui) : initShowInfoGUI() {_showInfo = gui;}
	
	
	
	/*
	 * Rebuild / Updating the GUI
	 */
	pointcut updateAll() : 
		call(void totalChannels(..)) || //&& within(CameoConsole)
		call(void load(String));
	
	pointcut rebuildAll() : 
		call(void totalChannels(..)) ||
		call(void load(String));
	
	pointcut updateShowInfo() : 
		call(void showTitle(..)) ||
		call(void showComment(..));
	
	pointcut rebuildLive() :
		call(void channelsPerLine(..)) ||
		call(void channelsHGroup(..)) ||
		call(void channelsVGroup(..)) ||
		call(void patchChan(..)) ||
		call(void unpatchChan(..)) ||
		call(void unpatchDim(..)) ||
		call(void patch1to1(..)) ||
		call(void patchClear(..));
	
	pointcut updateCues() :
		call(void go(..)) ||
		call(void back(..)) ||
		call(void loadCue(..)) ||
		call(void recordCue(int, ConsoleTimeing, int)) ||
		call(void deleteCue(..)) ||
		call(void time(..)) ||
		call(void delay(..)) ||
		call(void follow(..)) ||
		call(void link(..)) ||
		call(void description(..));
	
	pointcut updateLive() :
		call(void LiveCue.update*(..)) ||
		call(void LiveCue.set*(..)) ||
		call(void LiveCue.plus(..)) ||
		call(void LiveCue.minus(..));
	
	//TODO add advise for this
	pointcut updateMiddleMouseCommand() : call(void middleMouseAction(..));	
	
	/*
	 * When to exicute update / rebuild groups
	 */
	//Rebuilding should always come first
	after() : rebuildAll() {rebuildAll();}
	after() : updateAll() {updateAll();}
	
	after() : updateShowInfo() {updateShowInfo();}
	after() : rebuildLive() {rebuildLive();}
	after() : updateCues() {updateCues();}
	//after() : updateLive() {updateLive();}
	
	
	
	/*
	 * update Rebuild groups
	 */
	public void updateAll() {
		updateChannel();	
		updateMagic();
		updateCueList();
		updateSSheet();
		updateGroups();
		updateShowInfo();
	}
	
	public void rebuildAll() {
		rebuildLive();
	}

	
	public void updateChannel() {_channels.update();}
	public void updateMagic() {_magicSheet.update();}
	public void updateCueList() {_cueList.update();}
	public void updateSSheet() {_spreadSheet.update();}
	public void updateGroups() {_groupsList.update();}
	public void updateShowInfo() {_showInfo.update();}

	public void updateLive() {
		updateChannel();
		updateMagic();
	}

	public void updateCues() {
		updateCueList();
		updateSSheet();
	}
	
	public void rebuildLive() {
		_channels.rebuild();
		_magicSheet.rebuild();
	}
}
