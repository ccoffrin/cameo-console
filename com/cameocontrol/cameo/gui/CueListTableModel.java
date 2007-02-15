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

import java.awt.Component;
import java.util.Iterator;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.JLabel;

import com.cameocontrol.cameo.action.ACTCueMod;
import com.cameocontrol.cameo.action.ACTCueMove;
import com.cameocontrol.cameo.action.ActionInterpreter;
import com.cameocontrol.cameo.control.ConsoleFade;
import com.cameocontrol.cameo.control.ConsoleInquiry;
import com.cameocontrol.cameo.control.CameoFade;
import com.cameocontrol.cameo.dataStructure.*;



public class CueListTableModel extends AbstractTableModel{
	private ConsoleInquiry _console;
	private ActionInterpreter _actInt;
	private boolean editable = true;
	
	private String[] columnNames = {"", "Cue #","Time","Delay","Follow","Link","Description"};
	
	CueListTableModel(ConsoleInquiry c, ActionInterpreter ai)
	{
		_console = c;
		_actInt = ai;
	}
	
	public String getColumnName(int col) {
        return columnNames[col];
    }
	
	public int getRowCount() {
		return _console.getTotalCues();	
	}

	public int getColumnCount() {
		return columnNames.length;
	}
	
	public boolean isCellEditable(int row, int col)
	{
		if(editable && (col == 1 || col == 6 || col == 4 || col == 5))
			return true;
		else 
			return false;
	}
	
	private ConsoleFade getCueIndexed(int index){
		Iterator<ConsoleFade> cues = _console.getCues();
		ConsoleFade fade = cues.next();
		for(int x=0; x<index && cues.hasNext(); x++)
			fade = cues.next();
		
		return fade;
	}
	
	public void setValueAt(Object Value, int row, int col){
		ConsoleFade fade = getCueIndexed(row);
		
		ACTCueMod mod = new ACTCueMod();
		mod.setCueNumber(fade.getNumber());
		
		switch(col){
		case 1:
			_actInt.interprete(new ACTCueMove(fade.getNumber(), (int)(Float.parseFloat((String)Value)*1000)));
			break;
		
		case 4:
			if(((String)Value).length() > 0){
				mod.setFollowTime((int)(Float.parseFloat((String)Value)*1000));
				_actInt.interprete(mod);
			}
			break;
		
		case 5:	
			if(((String)Value).length() > 0){
				mod.setNextCue((int)(Float.parseFloat((String)Value)*1000));
				_actInt.interprete(mod);
			}
			break;
		
		case 6:
			mod.setDescription((String)Value);
			_actInt.interprete(mod);
			break;
		}
			
	}
	
	public Object getValueAt(int row, int col) {
		//TODO possibly the GUI needs to maintain the Row to Cue number mapping.. Or use through an interator?
		//Maybe the cue list has a method at makes vector of it's cues?
		ConsoleFade cue = getCueIndexed(row);
		
		switch(col)
		{
			case 0: 
				if(_console.isCurrentCue(cue.getNumber()))
					return ">";
				else
					return "";
			case 1: return Float.toString(cue.getNumber() / (float)1000);
			case 2: return cue.getTimeing().timeToString();
			case 3: return cue.getTimeing().delayToString();
			case 4:
				if (cue.getTimeing().getFollowTime() < 0)
					return "";
				else
					return Float.toString(cue.getTimeing().getFollowTime() / (float)1000);
			case 5: 
				if (cue.getNextCue() == null)
					return "";
				else
					return Float.toString(cue.getNextCue().getNumber() / (float)1000);
			case 6: return cue.getCue().getDiscription();
			
		}
		return null;
	}
	
	public void setEditable(boolean b) {editable = b;}
	public boolean getEditable() {return editable;}
}
