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

package com.cameocontrol.cameo.resource;

import java.util.StringTokenizer;

import com.cameocontrol.cameo.control.CameoCueList;
import com.cameocontrol.cameo.control.CameoMagicSheet;
import com.cameocontrol.cameo.control.CameoPrefrences;
import com.cameocontrol.cameo.control.CameoSettings;
import com.cameocontrol.cameo.control.CameoShow;
import com.cameocontrol.cameo.control.ConsoleSettings.RecordMode;
import com.cameocontrol.cameo.file.PrefrencesIO;
import com.cameocontrol.cameo.file.ShowIO;
import com.cameocontrol.cameo.file.adt.PrefrencesData;
import com.cameocontrol.cameo.file.adt.ShowFileData;
import com.cameocontrol.cameo.output.CameoPatch;

//TODO this class managed the conduate between the file-system, console, and output controler
/*
 * inputs eather a show file location or nothing and makes available,
 * - Cameo prefrences
 * - Show Settings
 * - Show Patch
 * - Show CueList
 * - Show MagicSheet
 * 
 */
public class CameoResourceManager implements ResourceManager {
	private CameoPrefrences _prefs;
	private CameoSettings _settings;
	private CameoPatch _patch;
	private CameoCueList _cueList;
	private CameoMagicSheet _magicSheet;
	
	private PrefrencesIO _prefIO;
	private ShowIO _showIO;
	
	public CameoResourceManager(){
		_prefIO = new PrefrencesIO();
		_showIO = new ShowIO();
		//TODO magic here
		_prefs = new CameoPrefrences();
		_settings = new CameoSettings();
		_patch = new CameoPatch();
		_cueList = new CameoCueList();
		_magicSheet = new CameoMagicSheet();
		
		
		PrefrencesData pd = _prefIO.loadPrefrences();
		_prefs.extractFrom(pd);

		if(_prefs.getLastSavedName().length() > 4){
			ShowFileData cs = _showIO.loadShow(_prefs.getShowPath()+System.getProperty("file.separator")+_prefs.getLastSavedName());
			_settings.extractFrom(cs.getSettings());
			_patch.extractFrom(cs.getPatch());
			_cueList.extractFrom(cs.getCueList());
			_magicSheet.extractFrom(cs.getMagicSheet());
		}
	}
		
	public CameoPrefrences getPrefrences() {return _prefs;}
	public CameoSettings getSettings() {return _settings;}
	public CameoPatch getPatch() {return _patch;}
	public CameoCueList getCueList() {return _cueList;}
	public CameoMagicSheet getMagicSheet() {return _magicSheet;}
	
	public CameoShow getShow() {return new CameoShow(_settings, _cueList, _magicSheet);}

	public void savePrefrences() {
		_prefIO.savePrefrences(_prefs.distill());
	}

	public void save() {saveAs(_prefs.getShowPath());}
	public void saveAs(String showPath) {
		ShowFileData show = new ShowFileData(_settings.distill(), _cueList.distill(), _patch.distill(), _magicSheet.distill());
		String lastSaved = _showIO.saveShow(showPath,show);
		_prefs.setLastSavedName(lastSaved);
		savePrefrences();
	}

	public void load(String fileName) {
		ShowFileData cs = _showIO.loadShow(fileName);
		
		_settings.extractFrom(cs.getSettings());
		_patch.extractFrom(cs.getPatch());
		_cueList.extractFrom(cs.getCueList());
		_magicSheet.extractFrom(cs.getMagicSheet());
		
		//return getShow();
	}
}
