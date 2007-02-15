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
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.AbstractTableModel;

import com.cameocontrol.cameo.gui.binder.KeyBinder;



public class CueListTable extends JTable{
	
	public CueListTable(AbstractTableModel atm, KeyBinder kb)
	{
		super(atm);	
		setAutoResizeMode(AUTO_RESIZE_OFF);
		setAutoscrolls(true);
		
		//TODO: upgrade this to support more screen widths
		getColumnModel().getColumn(0).setPreferredWidth(5);
		getColumnModel().getColumn(1).setPreferredWidth(70);
		getColumnModel().getColumn(2).setPreferredWidth(120);
		getColumnModel().getColumn(3).setPreferredWidth(120);
		getColumnModel().getColumn(4).setPreferredWidth(70);
		getColumnModel().getColumn(5).setPreferredWidth(70);
		getColumnModel().getColumn(6).setPreferredWidth(520);
		
		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		setSelectionBackground(Color.GRAY);
		setSelectionForeground(Color.WHITE);
		
		kb.bind(this);
	}
	/*
	 public TableCellRenderer getCellRenderer(int row, int column)
		{
			if(column == 0)
			{
				TableCellRenderer tcr = super.getCellRenderer(row, column);
				//tcr.
			}
			return super.getCellRenderer(row, column);
		}
	 
		private class rightJustfy extends JLabel implements TableCellRenderer
		{

			public Component getTableCellRendererComponent(JTable table, Object color, boolean isSelected, boolean hasFocus, int row, int column) {
				//color // TODO Auto-generated method stub
				//if(0 == column)
					this.setHorizontalTextPosition(JLabel.RIGHT);
				//else
				//	this.setHorizontalTextPosition(JLabel.LEFT);
				
				return this;
			}
		}
		*/
}
