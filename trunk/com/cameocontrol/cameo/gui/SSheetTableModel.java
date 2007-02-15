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

package com.cameocontrol.cameo.gui;

import javax.swing.table.AbstractTableModel;

import com.cameocontrol.cameo.control.ConsoleFade;
import com.cameocontrol.cameo.control.ConsoleInquiry;
import com.cameocontrol.cameo.control.CameoFade;

import java.util.Iterator;
import java.util.Vector;

public class SSheetTableModel extends AbstractTableModel{
	private ConsoleInquiry _console;
	private boolean editable = false;
	private LevelToString _levelTrans;

	private Vector<String> columnNames; 
	
	SSheetTableModel(ConsoleInquiry c)
	{
		_console = c;
		columnNames = new Vector<String>();
		_levelTrans = new LevelToString();

		columnNames.add("");
		columnNames.add("Cue #");
		for(int x = 0; x < _console.getTotalChannels(); x++)
			columnNames.add(Integer.toString(x+1));
	}
	
	public String getColumnName(int col) {
        return columnNames.get(col);
    }
	
	public int getRowCount() {
		return _console.getTotalCues();	
	}

	public int getColumnCount() {
		return columnNames.size();
	}
	
	
	public boolean isCellEditable(int row, int col)
	{
		//if(editable && (col > 1))
		//	return true;
		//else 
			return false;
	}
	
	/*
	public void setValueAt(Object Value, int row, int col)
	{
		CueTransition cue = _console.cueList().getCueIndexed(row);
		
		switch(col)
		{
		case 1: 
			_console.removeCue(cue);
			cue.setNumber((int)(Float.parseFloat((String)Value)*100));
			_console.addCue(cue);
			break;
		case 4:	cue.getTimeing().setFollowTime((int)(Float.parseFloat((String)Value)*1000)); break;
		case 6:	cue.setDiscription((String)Value); break;
		}
			
	}*/
	
	private ConsoleFade getCueIndexed(int index){
		Iterator<ConsoleFade> cues = _console.getCues();
		ConsoleFade fade = cues.next();
		for(int x=0; x<index  && cues.hasNext(); x++)
			fade = cues.next();
		
		return fade;
	}
	
	
	public Object getValueAt(int row, int col) {
		ConsoleFade cue = getCueIndexed(row);
		
		switch(col)
		{
			case 0: 
				if(_console.isCurrentCue(cue.getNumber()))
					return ">";
				else
					return "";
			case 1: return Float.toString(cue.getNumber() / (float)1000);
			default : 
				if(cue.getCue().getChannel(col - 2).getLevel() >= 0)
					return _levelTrans.toString(cue.getCue().getChannel(col - 2).getLevel());
				else
					return "";
		}
	}
	
	/*
	public void setEditable(boolean b) {editable = b;}
	public boolean getEditable() {return editable;}
	*/
}
