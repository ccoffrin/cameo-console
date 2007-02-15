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
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;

import org.jgraph.JGraph;

import com.cameocontrol.cameo.action.ActionInterpreter;
import com.cameocontrol.cameo.control.*;
import com.cameocontrol.cameo.dataStructure.jgraph.JGraphFactory;
import com.cameocontrol.cameo.dataStructure.jgraph.JGraphFactory.Kind;
import com.cameocontrol.cameo.gui.binder.KeyBinder;
import com.cameocontrol.cameo.gui.update.Rebuildable;
import com.cameocontrol.cameo.output.CameoPatch;





public class ChannelGUI extends JScrollPane implements ActionListener, Rebuildable{
	ConsoleInquiry _console;
	ActionInterpreter _actInt;
	KeyBinder _binder;
	
	public ChannelGUI(ConsoleInquiry ci, ActionInterpreter ai,  KeyBinder kb)
	{
		super((new JGraphFactory(ci, ai)).buildChannelJGraph(Kind.CHANNEL, null, kb, ci.getLiveCue(), ci.getSettings()));
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		getViewport().setBackground(Color.BLACK);
		
		_console = ci;
		_actInt = ai;
		_binder = kb;
		//kb.bind(getViewport());
		Timer _timer = new Timer(_console.getPrefrences().getOutputRefesh(), this);
		_timer.start();
	}
	
	public void rebuild() {
		this.setViewportView((new JGraphFactory(_console, _actInt)).buildChannelJGraph(Kind.CHANNEL, null, _binder, _console.getLiveCue(), _console.getSettings()));
	}
	
	public void update() {updateUI();}

	public void actionPerformed(ActionEvent arg0) {
		//console.updateLive();
		getViewport().updateUI();
		//updateUI();
	}
}
