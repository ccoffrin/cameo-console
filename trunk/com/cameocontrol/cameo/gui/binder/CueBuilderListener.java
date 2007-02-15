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


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.cameocontrol.cameo.gui.CommandLine;

public class CueBuilderListener implements MouseListener {
	private String _at;
	private CommandLine _cmdLine;
	
	public CueBuilderListener(CommandLine cl, String atLevel) {
		_cmdLine = cl;
		_at = atLevel;
	}
	
	public void mouseClicked(MouseEvent e) {
		switch(e.getButton()){
		case MouseEvent.BUTTON2:
			_cmdLine.appendExecute(_at);
			break;
		//case MouseEvent.BUTTON3:
		//	_cmdLine.execute();
		//	break;
		default:
		}
	}

	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
