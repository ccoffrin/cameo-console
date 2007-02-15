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

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;

import com.cameocontrol.cameo.action.ActionInterpreter;
import com.cameocontrol.cameo.control.ConsoleInquiry;
import com.cameocontrol.cameo.dataStructure.jgraph.JGraphFactory;
import com.cameocontrol.cameo.dataStructure.jgraph.JGraphFactory.Kind;
import com.cameocontrol.cameo.gui.binder.KeyBinder;
import com.cameocontrol.cameo.gui.update.Rebuildable;




public class MagicSheetGUI extends JScrollPane implements ActionListener, Rebuildable {
	private ConsoleInquiry _console;
	private ActionInterpreter _actInt;
	private KeyBinder _binder;
	
	public MagicSheetGUI(ConsoleInquiry ci, ActionInterpreter ai, CommandLine cmd, KeyBinder kb)
	{
		super((new JGraphFactory(ci, ai)).buildChannelJGraph(Kind.MAGIC, ci.getMagicSheet(), kb, ci.getLiveCue(), ci.getSettings()));
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		getViewport().setBackground(Color.BLACK);
		
		_console = ci;
		_actInt = ai;
		_binder = kb;
		_binder.bind(this);
		
		Timer _timer = new Timer(_console.getPrefrences().getOutputRefesh(), this);
		_timer.start();
		
		/*
		this.addMouseListener(new CueBuilderListener(cmd));
		this.addMouseWheelListener(new CueBuilderWheelListener(cmd));
		*/
	}

	public void rebuild() {
		this.setViewportView((new JGraphFactory(_console, _actInt)).buildChannelJGraph(Kind.MAGIC, _console.getMagicSheet(), _binder, _console.getLiveCue(), _console.getSettings()));
	}
	
	public void update() {updateUI();}
	
	public void actionPerformed(ActionEvent arg0) {
		getViewport().updateUI();
	}
}
