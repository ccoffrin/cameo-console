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

import javax.swing.AbstractAction;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;

import com.cameocontrol.cameo.action.ActionInterpreter;
import com.cameocontrol.cameo.control.Console;
import com.cameocontrol.cameo.control.ConsoleInquiry;
import com.cameocontrol.cameo.dataStructure.CommandLineMessage;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;



public class CommandLine extends JTextField implements ActionListener {
//public class CommandLine extends JTextField implements ActionListener {
	private boolean _errMsg;
	private ActionInterpreter _actInt;
	private String _lastCommand;
	
	public  CommandLine(ActionInterpreter ai) {
		//super("<HTML><font color=red>RED</font></HTML>");
		super();
		_errMsg = false;
		_actInt = ai;
		_lastCommand = "";
		
		 setBackground(Color.BLACK);
	     setForeground(Color.WHITE);
	     //addActionListener(this);
	     setFocusable(false);
	     //this.addAncestorListener(this);
	     //this.addAncestorListener()
	     //this.getT
	     //this.setName("<HTML><font color=red>RED</font></HTML>");
	     /*
	     this.resetKeyboardActions();
	    		
		 new KeyBinderRoot(this, this).bind();
		   
		 getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "execute");
		 getActionMap().put("execute", new ExeAction());
		
		 getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "del");
		 getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), "del");
		 getActionMap().put("del",  new DelAction());
	      */
	}
	
	public void setLastCommand() {
		setForeground(Color.WHITE);
		updateUI();
		setText(_lastCommand);
		_errMsg = false;
	}
	
	public void delete() {
		if(_errMsg) {
			setForeground(Color.WHITE);
			updateUI();
			//setText(s);
			setText("");
			_errMsg = false;
		}
		else
			setText(deleteOne(getText()));
	}
	
	public void execute() {
		if(_errMsg) {
			setText("");
			_errMsg = false;
		}
		else {
			//System.out.println(_cmdLine.getText());
			CommandLineMessage err = _actInt.interprete(getText());
			System.out.println("**"+getText()+"**");
			if(err == null){
				if(getText().length() > 0) //so extra enters don't wipe out last command
					_lastCommand = getText();
				setText("");
			}
			else{
				//TODO This is not making error text red (try making textfield rich text field)
				if(err.isError()){
					_errMsg = true;
					setForeground(Color.RED);
					updateUI();
				} 
				//else if(err.isFeedback()) {}
				
				
				setText(err.getMessage());
				//setForeground(Color.WHITE);
				//this.updateUI();
				
			}
		}
	}
	
	public void appendExecute(String s) {
		setText(getText()+s+" ");
		execute();
	}
	
	private String deleteOne(String s) {
		if(s.length() < 2)
			return "";
		int end = s.length() - 2;
		char temp = s.charAt(end);
		
		while(temp != ' ')
			if(end == 0)
				return " ";
			else
				temp = s.charAt(--end);
		
		return s.substring(0, end+1);
	}

	public void actionPerformed(ActionEvent e) {
		//if (getAction().equals(e))
			execute();
	}
	
	//private class ExeAction extends AbstractAction {public void actionPerformed(ActionEvent e) {execute();}}
	//private class DelAction extends AbstractAction {public void actionPerformed(ActionEvent e) {delete();}}
	
}
