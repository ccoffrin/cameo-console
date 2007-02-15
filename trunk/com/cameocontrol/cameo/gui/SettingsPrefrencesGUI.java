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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import com.cameocontrol.cameo.action.ACTSettingsPrefMod;
import com.cameocontrol.cameo.action.ActionInterpreter;
import com.cameocontrol.cameo.control.ConsoleInquiry;
import com.cameocontrol.cameo.control.ConsolePrefrences;



public class SettingsPrefrencesGUI extends JPanel implements ActionListener {
	private ConsoleInquiry _console;
	private ActionInterpreter _actInt;

	private JTextField _mouseMiddleAction;
	private JTextField _refreshOutputRate;
	private JTextField _startCode;
	private JTextField _savePath;
	private JTextField _fileName;

	private JButton _apply;

	private String _initMousemiddleAction;
	private int _initHRefreshOutputRate;
	private int _initStartCode;
	

	public SettingsPrefrencesGUI(ConsoleInquiry c, ActionInterpreter ai){
		_console = c;
		_actInt = ai;
		ConsolePrefrences cp = _console.getPrefrences();
		
		setLayout(new GridBagLayout());
		GridBagConstraints cons = new GridBagConstraints();
		
		cons.fill = GridBagConstraints.BOTH;
	    
		cons.gridx = 0;
		cons.gridy = 0;
		add(new JLabel("Mouse Wheel Pressed Action"), cons);
	    cons.gridx = 1;
	    add(_mouseMiddleAction = new JTextField(_initMousemiddleAction = cp.getMouseMiddleAction()), cons);

		cons.gridx = 0;
		cons.gridy = 1;
		add(new JLabel("Refresh Rate"), cons);
	    cons.gridx = 1;
	    add(_refreshOutputRate = new JTextField(Integer.toString(_initHRefreshOutputRate = cp.getOutputRefesh())), cons);
	    
		cons.gridx = 0;
		cons.gridy = 2;
		add(new JLabel("Start Code"), cons);
	    cons.gridx = 1;
	    add(_startCode = new JTextField(Integer.toString(_initStartCode = cp.getStartCode())), cons);

		cons.gridx = 0;
		cons.gridy = 3;
		add(new JLabel("Save Path"), cons);
	    cons.gridx = 1;
	    add(_savePath = new JTextField(cp.getShowPath()), cons);
	    _savePath.disable();

		cons.gridx = 0;
		cons.gridy = 4;
		add(new JLabel("Last Saved"), cons);
	    cons.gridx = 1;
	    add(_fileName = new JTextField(cp.getLastSavedName()), cons);
	    _fileName.disable();

	    cons.gridx = 0;
		cons.gridy = 5;
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
		
		ConsolePrefrences cp = _console.getPrefrences();
		
		if (event.getSource().equals(_apply)) {
			String mmAction = _mouseMiddleAction.getText();
			int refreshRate = Integer.parseInt(_refreshOutputRate.getText());
			int startCode = Integer.parseInt(_startCode.getText());
			
			ACTSettingsPrefMod mod = new ACTSettingsPrefMod();
			
    			if(mmAction != _initMousemiddleAction)
    				mod.setMiddleMouseAction(mmAction);
    			if(refreshRate != _initHRefreshOutputRate)
    				mod.setRefreshRate(refreshRate);
    			if(startCode != _initStartCode)
    				mod.setStartCode(startCode);
    			
    			_actInt.interprete(mod);
    			
    			
    			JOptionPane.showMessageDialog(this, "Cameo must be restarted before the prefrence changes will take effect.");
    		
    			 cp = _console.getPrefrences();
    			//update fields

    			_mouseMiddleAction.setText(_initMousemiddleAction = cp.getMouseMiddleAction());
    			_refreshOutputRate.setText(Integer.toString(_initHRefreshOutputRate = cp.getOutputRefesh()));
    			_startCode.setText(Integer.toString(_initStartCode = cp.getStartCode()));    			
    		}
    }
}
