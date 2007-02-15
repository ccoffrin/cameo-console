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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JScrollPane;

import com.cameocontrol.cameo.action.ACTPatch1to1;
import com.cameocontrol.cameo.action.ActionInterpreter;
import com.cameocontrol.cameo.control.ConsoleInquiry;



public class PatchGUI extends JScrollPane {
	//private JButton _1to1;
	
	public PatchGUI(ConsoleInquiry c, ActionInterpreter ai)
	{
		super(new PatchTable(new PatchTableModel(c, ai), c, ai));
		//super();
		//getViewport().setLayout(new BorderLayout());
		//getViewport().add(new PatchTable(new PatchTableModel(c, ai), c, ai),BorderLayout.CENTER);
		//scrollTemp =  new JScrollPane(new SSheetTable(new SSheetTableModel(c), c));
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
         getViewport().setBackground(Color.BLACK);
         
         /*
         _1to1 = new JButton("Patch 1 to 1");
 		_1to1.setActionCommand("Reset Patch 1 to 1");
 		_1to1.setBackground(Color.BLACK);
 		//deleteCue.setForeground(Color.GREEN);
 		//_1to1.addActionListener(this);
 		*/
	}

	/*
	public void actionPerformed(ActionEvent e) {
		
		if (_1to1.getActionCommand().equals(e.getActionCommand()))
		{
			_actInt.interprete(new ACTPatch1to1());
			
			if(table.getSelectedRow() >= 0)
			{
				CueTransition cue = console.cueList().getCueIndexed(table.getSelectedRow());
				
				int choice = JOptionPane.showConfirmDialog(this, "Delete Cue " + cue.getNumberString()  + "?", "", JOptionPane.YES_NO_OPTION);		
				if (choice == 0) //selected yes
				{
					System.out.println(choice);
					console.removeCue(cue);
					//table.updateUI();
				}
			}
			*/
		//}
		/*
		else if (editable.getActionCommand().equals(e.getActionCommand()))
		{
			if(tableModel.getEditable())
			{
				tableModel.setEditable(false);
				editable.setText("Edit");
			}
			else
			{
				tableModel.setEditable(true);
				editable.setText("Lock");
			}
		}
		
	}*/
}
