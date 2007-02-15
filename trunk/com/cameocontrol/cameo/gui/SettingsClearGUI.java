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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.cameocontrol.cameo.action.ACTCuesClear;
import com.cameocontrol.cameo.action.ACTPatch1to1;
import com.cameocontrol.cameo.action.ACTPatchClear;
import com.cameocontrol.cameo.action.ActionInterpreter;
import com.cameocontrol.cameo.control.ConsoleInquiry;
import com.cameocontrol.cameo.control.ConsoleSettings;


public class SettingsClearGUI  extends JPanel implements ActionListener {
	private ConsoleInquiry _console;
	private ActionInterpreter _actInt;

	private JButton _cues;
	private JButton _unpatch;
	private JButton _patch;

	public SettingsClearGUI(ConsoleInquiry c, ActionInterpreter ai){
		_console = c;
		_actInt = ai;
	
		setLayout(new GridBagLayout());
		GridBagConstraints cons = new GridBagConstraints();
		
		cons.fill = GridBagConstraints.BOTH;

	    cons.gridx = 0;
		cons.gridy = 0;
	    add(_cues = new JButton("Delete All Cues"), cons);
	    _cues.addActionListener(this);
	    
	    cons.gridx = 0;
		cons.gridy = 1;
	    add(_unpatch = new JButton("Unpatch All"), cons);
	    _unpatch.addActionListener(this);
	    
	    cons.gridx = 0;
		cons.gridy = 2;
	    add(_patch = new JButton("Patch 1 to 1"), cons);
	    _patch.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent event) 
    {
		if (event.getSource().equals(_cues)) {
//			default icon, custom title
			int n = JOptionPane.showConfirmDialog(
			    this,
			    "This option cannot be undone, are you sure?",
			    "Cue Clear Warning",
			    JOptionPane.YES_NO_OPTION);
			if(n == 0)
    				_actInt.interprete(new ACTCuesClear());
  
    		} else if(event.getSource().equals(_unpatch)) {
    			int n = JOptionPane.showConfirmDialog(
    				    this,
    				    "This option cannot be undone, are you sure?",
    				    "Patch Clear Warning",
    				    JOptionPane.YES_NO_OPTION);
    			if(n == 0)
    				_actInt.interprete(new ACTPatchClear());
    		} else if(event.getSource().equals(_patch)) {
    			int n = JOptionPane.showConfirmDialog(
    				    this,
    				    "This option cannot be undone, are you sure?",
    				    "Patch 1 to 1 Warning",
    				    JOptionPane.YES_NO_OPTION);
    			if(n == 0)
    				_actInt.interprete(new ACTPatch1to1());
    		}
    }
}

