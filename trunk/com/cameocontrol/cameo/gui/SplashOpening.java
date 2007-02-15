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

import javax.swing.JFrame;
import javax.swing.JLabel;

public class SplashOpening {
	private JFrame	_openingFrame;
	
	public void run(){
		 _openingFrame = new JFrame("Loading Cameo");
		
		JLabel text = new JLabel("This is the splash graphic");
		text.setHorizontalAlignment(text.CENTER);
		text.setVerticalAlignment(text.CENTER);
		//text.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		//text.setAlignmentY(JComponent.CENTER_ALIGNMENT);
		text.setBackground(Color.BLACK);
		text.setForeground(Color.WHITE);
		_openingFrame.add(text);
		
		_openingFrame.setBackground(Color.BLACK);
		
		_openingFrame.pack();
		_openingFrame.setSize(new Dimension(300,500));
	    
		_openingFrame.setLocationRelativeTo(null);
		_openingFrame.setVisible(true); //*/
	}
	
	public void close(){
		if(_openingFrame != null)
			_openingFrame.dispose();
	}
}
