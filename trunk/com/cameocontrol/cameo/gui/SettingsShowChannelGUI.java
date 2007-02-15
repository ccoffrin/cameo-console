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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.cameocontrol.cameo.action.ACTSettingsShowChannelMod;
import com.cameocontrol.cameo.action.ActionInterpreter;
import com.cameocontrol.cameo.control.ConsoleInquiry;
import com.cameocontrol.cameo.control.ConsoleSettings;
import com.cameocontrol.cameo.control.ConsoleSettings.RecordMode;


public class SettingsShowChannelGUI extends JPanel implements ActionListener {
	private ConsoleInquiry _console;
	private ActionInterpreter _actInt;

	private JTextField _perLine;
	private JTextField _hGrouping;
	private JTextField _vGrouping;

	private JButton _apply;

	private int _initPerLine;
	private int _initHGrouping;
	private int _initVGrouping;
	
	//TODO convert to tapped pane window, where this is general tab, 
	//this would be under Channels View
	/*
	int _ChannelsPerLine;
	int _ChannelGrouping;
	int _LineGrouping;
	 */

	public SettingsShowChannelGUI(ConsoleInquiry c, ActionInterpreter ai){
		_console = c;
		_actInt = ai;
		ConsoleSettings cs = _console.getSettings();

		/*
		JTextArea text = new JTextArea("This is the show settings");
		text.setBackground(Color.BLACK);
		text.setForeground(Color.WHITE);
		add(text);
		*/
		
		//setBackground(Color.BLACK);
		
		setLayout(new GridBagLayout());
		GridBagConstraints cons = new GridBagConstraints();
		
		cons.fill = GridBagConstraints.BOTH;
	    
		cons.gridx = 0;
		cons.gridy = 0;
		add(new JLabel("Channels per Line"), cons);
	    cons.gridx = 1;
	    add(_perLine = new JTextField(Integer.toString(_initPerLine = cs.getChannelsPerLine())), cons);

		cons.gridx = 0;
		cons.gridy = 1;
		add(new JLabel("Channels per Horizontal Group"), cons);
	    cons.gridx = 1;
	    add(_hGrouping = new JTextField(Integer.toString(_initHGrouping = cs.getChannelGrouping())), cons);
	    
		cons.gridx = 0;
		cons.gridy = 2;
		add(new JLabel("Channels per Vertical Group"), cons);
	    cons.gridx = 1;
	    add(_vGrouping = new JTextField(Integer.toString(_initVGrouping = cs.getLineGrouping())), cons);

	    cons.gridx = 0;
		cons.gridy = 3;
		// add(_cancel = new JButton("Cancel"), cons);
	    cons.gridx = 1;
	    add(_apply = new JButton("Apply"), cons);
	    
	    _apply.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent event) 
    {
    		/*if(event.getSource().equals(_cancel)) {
    			this.removeNotify();
    			//frame.dispose();
    		} else */
		
		//(int)(Float.parseFloat((String)Value)*1000)
		
		ConsoleSettings cs = _console.getSettings();
		
		if (event.getSource().equals(_apply)) {
			int perLine = Integer.parseInt(_perLine.getText());
			int hGrouping = Integer.parseInt(_hGrouping.getText());
			int vGrouping = Integer.parseInt(_vGrouping.getText());
			
			ACTSettingsShowChannelMod mod = new ACTSettingsShowChannelMod();
			
    			if(perLine != _initPerLine)
    				mod.setPerLine(perLine);
    			if(hGrouping != _initHGrouping)
    				mod.setPerHGroup(hGrouping);
    			if(vGrouping != _initVGrouping)
    				mod.setPerVGroup(vGrouping);
    			
    			_actInt.interprete(mod);
    			
    			//update fields
    			 cs = _console.getSettings();

    			_perLine.setText(Integer.toString(_initPerLine = cs.getChannelsPerLine()));
    			_hGrouping.setText(Integer.toString(_initHGrouping = cs.getChannelGrouping()));
    			_vGrouping.setText(Integer.toString(_initVGrouping = cs.getLineGrouping()));    			
    		}
    }
}
