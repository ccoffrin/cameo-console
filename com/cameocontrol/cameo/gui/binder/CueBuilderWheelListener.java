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


import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import com.cameocontrol.cameo.gui.CommandLine;

public class CueBuilderWheelListener implements MouseWheelListener {
	//private String plusf = "@ +5";
	//private String minusf = "@ -5";
	private int factor = 10;
	
	private CommandLine _cmdLine;

	public CueBuilderWheelListener(CommandLine cl) {
		_cmdLine = cl;
	}
	
	public void mouseWheelMoved(MouseWheelEvent e) {
		String message;
	       int notches = e.getWheelRotation();
	       
	       if (notches < 0) 
	    	   	_cmdLine.appendExecute("@ +"+(-(notches*factor)));
	       else
	    		_cmdLine.appendExecute("@ -"+(notches*factor));
	       
	       /*if (notches < 0) {
           message = "Mouse wheel moved UP "
                        + -notches + " notch(es)" + newline;
	       } else {
	           message = "Mouse wheel moved DOWN "
	                        + notches + " notch(es)" + newline;
	       }*/
	       
	       /*
	       if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
	           message += "    Scroll type: WHEEL_UNIT_SCROLL" + newline;
	           message += "    Scroll amount: " + e.getScrollAmount()
	                   + " unit increments per notch" + newline;
	           message += "    Units to scroll: " + e.getUnitsToScroll()
	                   + " unit increments" + newline;
	           message += "    Vertical unit increment: "
	               + scrollPane.getVerticalScrollBar().getUnitIncrement(1)
	               + " pixels" + newline;
	       } else { //scroll type == MouseWheelEvent.WHEEL_BLOCK_SCROLL
	           message += "    Scroll type: WHEEL_BLOCK_SCROLL" + newline;
	           message += "    Vertical block increment: "
	               + scrollPane.getVerticalScrollBar().getBlockIncrement(1)
	               + " pixels" + newline;
	       }
	       saySomething(message, e);
	       */
	}

}
