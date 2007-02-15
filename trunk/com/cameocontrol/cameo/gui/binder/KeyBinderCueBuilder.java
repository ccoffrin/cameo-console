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

package com.cameocontrol.cameo.gui.binder;



import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import com.cameocontrol.cameo.gui.CommandLine;

public class KeyBinderCueBuilder extends KeyBinder {
	private String _atLevel;
	
	protected KeyBinderCueBuilder(CommandLine c, String al) {
		super(c);
		_atLevel = al;
	}
	
	public void bind(JComponent component) {
		super.bind(component);
		
		component.addMouseListener(new CueBuilderListener(_cmdLine, _atLevel));
		component.addMouseWheelListener(new CueBuilderWheelListener(_cmdLine));
		/*
		component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.BUTTON3_DOWN_MASK, 0), atfifty);
		component.getActionMap().put(atfifty, new atfiftyAction());
		
		component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.MOUSE_WHEEL_EVENT_MASK, 0), plusf);
		component.getActionMap().put(plusf, new plusfAction());
		
		component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.MOUSE_WHEEL_EVENT_MASK, 0), minusf);
		component.getActionMap().put(minusf, new minusfAction());
		
		
		component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent., 0), minusf);
		*/
			
	}
	     
	
	private String plusf = "@ +10";
	private String atfifty = "@ 50";
	private String minusf = "@ -10";
	
	private class atfiftyAction extends AbstractAction {public void actionPerformed(ActionEvent e) {append(atfifty); _cmdLine.postActionEvent();}}
	private class plusfAction extends AbstractAction {public void actionPerformed(ActionEvent e) {append(plusf); _cmdLine.postActionEvent();}}
	private class minusfAction extends AbstractAction {public void actionPerformed(ActionEvent e) {append(minusf); _cmdLine.postActionEvent();}}
}
