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



import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.cameocontrol.cameo.action.ActionInterpreter;
import com.cameocontrol.cameo.control.ConsoleInquiry;
import com.cameocontrol.cameo.dataStructure.*;
import com.cameocontrol.cameo.gui.binder.KeyBinder;
import com.cameocontrol.cameo.gui.update.Updateable;



public class CueListGUI extends JScrollPane implements ActionListener, Updateable {
	private JTable table;
	private CueListTableModel tableModel;
	private ConsoleInquiry console;
	private Toolkit tk = Toolkit.getDefaultToolkit();
	private JButton deleteCue;
	private JButton editable;
	
	public CueListGUI(ConsoleInquiry c, ActionInterpreter ai, KeyBinder kb)
	{
		super(new CueListTable(new CueListTableModel(c, ai), kb));
		console = c;
		
		//add(table, BorderLayout.CENTER);
		/*
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(tk.getScreenSize().width - 50, prefHight()));
		scrollPane.setBackground(Color.BLACK);
		this.add(scrollPane);
		*/
		setPreferredSize(new Dimension(tk.getScreenSize().width - 50, tk.getScreenSize().width - 100));
		
		deleteCue = new JButton("Delete Cue");
		deleteCue.setActionCommand("delete cue");
		deleteCue.setBackground(Color.BLACK);
		//deleteCue.setForeground(Color.GREEN);
		deleteCue.addActionListener(this);
		
		editable = new JButton("Edit");
		editable.setActionCommand("toggel edit");
		editable.setBackground(Color.BLACK);
		//deleteCue.setForeground(Color.GREEN);
		editable.addActionListener(this);
		
	    //setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        getViewport().setBackground(Color.BLACK);
        
		//add(deleteCue, BorderLayout.PAGE_END);
		//add(editable, BorderLayout.PAGE_END);
        //add(deleteCue);
        //add(editable);		
	}
	
	public void update() {this.getViewport().updateUI(); super.updateUI();}
	
	/*
	private int prefHight()
	{
		if(console.cueList().size() * 19 < tk.getScreenSize().height)
			return console.cueList().size() * 19;
		else
			return tk.getScreenSize().height;
	}
	*/
	
	public void actionPerformed(ActionEvent e) {
	/*
		if (deleteCue.getActionCommand().equals(e.getActionCommand()))
		{
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
		}
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
		*/
	}
	
}
