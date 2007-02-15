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

import java.util.Collection;
import java.util.Vector;
import java.util.StringTokenizer;

import javax.swing.table.AbstractTableModel;

import com.cameocontrol.cameo.action.ACTPatchChan;
import com.cameocontrol.cameo.action.ACTUnpatchChan;
import com.cameocontrol.cameo.action.ActionInterpreter;
import com.cameocontrol.cameo.control.ConsoleInquiry;


public class PatchTableModel extends AbstractTableModel{
	private ConsoleInquiry _console;
	private ActionInterpreter _actInt;
	private boolean editable = true;

	private Vector<String> columnNames; 
	
	PatchTableModel(ConsoleInquiry ci, ActionInterpreter ai)
	{
		_console = ci;
		_actInt = ai;
		columnNames = new Vector<String>();

		columnNames.add("Channel");
		columnNames.add("Dimmers");
	}
	
	public String getColumnName(int col) {
        return columnNames.get(col);
    }
	
	public int getRowCount() {
		return _console.getTotalChannels()+1;	
	}

	public int getColumnCount() {
		return columnNames.size();
	}
	
	
	public boolean isCellEditable(int row, int col)
	{
		if(row < _console.getTotalChannels())
			if(editable && (col > 0))
				return true;
		
		return false;
	}
	
	
	public void setValueAt(Object Value, int row, int col)
	{
		switch(col)
		{
		case 1: 
			_actInt.interprete(new ACTUnpatchChan(row+1));
			patchDims((String)Value, row);
			break;
		//case 4:	cue.getTimeing().setFollowTime((int)(Float.parseFloat((String)Value)*1000)); break;
		//case 6:	cue.setDiscription((String)Value); break;
		}
			
	}
	
	public Object getValueAt(int row, int col) {
		//CueTransition cue = _console.cueList().getCueIndexed(row);
		
		switch(col)
		{
			case 0: 
				if(row <_console.getTotalChannels())
					return "("+Integer.toString(row+1)+")";
				else
					return "Unpatched";
			case 1: 
				if(row <_console.getTotalChannels())
					return printIntArray(_console.getDimmers(row));
				else
					return printIntArray(_console.getUnpachedDims());
				
			//default : return Short.toString(cue.getChannel(col - 2).getLevel());
		}
		return null;
	}
	
	
	private String printIntArray(Collection<Integer> a){
		String s = "";
		//boolean first = false;
		for(Integer x : a)
			if(x == 0)
				s = Integer.toString(x+1);
			else
				s += " "+Integer.toString(x+1);
		return s+" ";	
	}
	
	private void patchDims(String s, int channel) {
		StringTokenizer dims = new StringTokenizer(s, " ");
		while(dims.hasMoreTokens())
			_actInt.interprete(new ACTPatchChan(channel+1, Integer.parseInt(dims.nextToken())));
	}
	/*
	public void setEditable(boolean b) {editable = b;}
	public boolean getEditable() {return editable;}
	*/
}
