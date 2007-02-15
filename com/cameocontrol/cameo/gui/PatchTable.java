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

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.cameocontrol.cameo.action.ActionInterpreter;
import com.cameocontrol.cameo.control.ConsoleInquiry;



//import action.ACTPatch1to1;

public class PatchTable extends JTable {
	
	public PatchTable(AbstractTableModel atm, ConsoleInquiry ci, ActionInterpreter ai)
	{
		super(atm);	
		
		setAutoResizeMode(AUTO_RESIZE_OFF);
		setAutoscrolls(true);
		
		getColumnModel().getColumn(0).setPreferredWidth(70);
		getColumnModel().getColumn(1).setPreferredWidth(150);
		//for(int x = 0; x < ci.getTotalChannels(); x++)
		//	getColumnModel().getColumn(x+2).setPreferredWidth(20);
		
		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		setSelectionBackground(Color.GRAY);
		setSelectionForeground(Color.WHITE);
	
	}
}
