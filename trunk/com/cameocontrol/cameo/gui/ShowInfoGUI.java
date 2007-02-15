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

import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.JTextField;

import com.cameocontrol.cameo.control.ConsoleInquiry;
import com.cameocontrol.cameo.gui.update.Updateable;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimerTask;
import java.util.Timer;

public class ShowInfoGUI extends JLabel implements Updateable {
	private ConsoleInquiry _console;
	private Calendar _calendar;
	private String _dateFormat = "HH:mm";
	private SimpleDateFormat _format;
	private JComponent root;
	//private JLabel _this;
	
	public ShowInfoGUI(ConsoleInquiry ci) {
		//_this = this;
		_console = ci;
		_calendar = Calendar.getInstance();	 
		_format = new SimpleDateFormat(_dateFormat);     
		
		setText(makeText());
		//setBackground(Color.BLACK);
		//setForeground(Color.WHITE);
		setHorizontalAlignment(CENTER);
		
		//setAlignmentX(CENTER_ALIGNMENT);
		
		new Timer().schedule(new UpdateFromOutput(), 0, 60000);
	}
	
	private String makeText() {return _console.getSettings().getTitle()+"   "+_format.format(_calendar.getTime())+"   "+_console.getSettings().getComment();}
	
	public void update() {
		_calendar = Calendar.getInstance();
		setText(makeText());
		updateUI();
	}
	
	private class UpdateFromOutput extends TimerTask {
		public void run() {
			_calendar = Calendar.getInstance();
			setText(makeText());
			updateUI();
			//root.updateUI();
		}
	}
	
}
