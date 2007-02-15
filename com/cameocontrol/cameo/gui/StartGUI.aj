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

import com.cameocontrol.cameo.Cameo;
import com.cameocontrol.cameo.control.CameoConsole;
import com.cameocontrol.cameo.control.ConsoleInquiry;
import com.cameocontrol.cameo.action.ActionInterpreter;
import com.cameocontrol.cameo.gui.menu.MenuFactory;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JOptionPane;
import javax.swing.JFrame;

/**
 * This aspect takes starts the GUI when the console starts up
 */
public aspect StartGUI {
	private SplashOpening _splash;
	private ConsoleInquiry _console;
	
	pointcut appStart() : call(void Cameo.run());
	pointcut consoleInitialization() : call(CameoConsole.new(..));
	pointcut ActionInterpInitialization() : call(ActionInterpreter.new(..));
	
	before() : appStart(){
		_splash = new SplashOpening();
		_splash.run();
	}
	after() returning (CameoConsole console): consoleInitialization(){
		_console = console;
	}
	
	after() returning (ActionInterpreter actIntp): ActionInterpInitialization(){
		if(_console.isSimulation())
			JOptionPane.showMessageDialog(new JFrame("Warning"), "No DMX output device detected, running in simulation mode.");
	
		CameoGUI gui = new CameoGUI(_console, actIntp);
		//_guiUpdater = gui.getUIUpdater();
		//actInt.interprete(new ACTAddUpdater(gui.getUIUpdater()));
	
		//Running in Applet mode
		//this.setJMenuBar(gui.getMenuBar()); 
		//this.add(gui);
		
		//	Stant alone application mode, uncomment this code and the imports above
		
		JFrame frame = new JFrame("Cameo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setJMenuBar(new MenuFactory(gui, new WindowBuilder(_console, actIntp)).makeMenuBar());
		frame.getContentPane().add(gui);
		
		frame.pack();
		//gui.requestCMDLineFocus();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(screenSize);
	    
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true); //
	    
	    _splash.close();
	}
	
	
	
}
