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

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.cameocontrol.cameo.action.ACTLoadShow;
import com.cameocontrol.cameo.action.ACTSaveShow;
import com.cameocontrol.cameo.action.ACTSettingsPrefMod;
import com.cameocontrol.cameo.action.ActionInterpreter;
import com.cameocontrol.cameo.control.ConsoleInquiry;



public class WindowBuilder {
	private ConsoleInquiry _console;
	private ActionInterpreter _actInt; 
	//private CommandLine _cmdLine;
	
	public WindowBuilder(ConsoleInquiry ci, ActionInterpreter ai){
		_console = ci;
		_actInt = ai;
		//_cmdLine = cmd;
	}
	
	public void build(JComponent content, String name) {
		
		//AtGUI atGui = new AtGUI(bb);
		JFrame frame1 = new JFrame(name);
		//frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//MenuFactory mf = ;
		//frame1.setJMenuBar(new MenuFactory(gui).makeMenuBar());
		frame1.getContentPane().add(content);
		
		frame1.pack();
		
	    frame1.setLocationRelativeTo(null);
	    frame1.setVisible(true); //*/
	}
	
	public void buildSave() {_actInt.interprete(new ACTSaveShow());}
	
	public void buildSaveDiag() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
       	fileChooser.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
       	fileChooser.setCurrentDirectory(new File(_console.getPrefrences().getShowPath()));
       	JFrame panel = new JFrame();   		
       	
      	int returnVal = fileChooser.showSaveDialog(panel);

         if (returnVal == JFileChooser.APPROVE_OPTION) 
        {
            File file = fileChooser.getSelectedFile();
            //This is where a real application would open the file.

           _actInt.interprete(new ACTSaveShow(file.getAbsolutePath()));
            
          //  JOptionPane.showMessageDialog(panel, file.getName() + " Saved.");
            
        } 
        else 
        {
        		//JOptionPane.showMessageDialog(frame,"Save command cancelled by user.");
        }
	}
	
	public void buildLoadDiag() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setFileSelectionMode(JFileChooser.OPEN_DIALOG);
		fileChooser.setCurrentDirectory(new File(_console.getPrefrences().getShowPath()));
		
   		int returnVal = fileChooser.showOpenDialog(new JFrame("load"));

        if (returnVal == JFileChooser.APPROVE_OPTION) 
        {
            File file = fileChooser.getSelectedFile();
            //This is where a real application would open the file.
            //JOptionPane.showMessageDialog(frame,"Opening: " + file.getName() + ".");
    		
            _actInt.interprete(new ACTLoadShow(file.getAbsolutePath()));
				
        } 
        else 
        {
        		//JOptionPane.showMessageDialog(frame,"Open command cancelled by user.");
        }
	}
	
	public void buildPatch() {
		build(new PatchGUI(_console, _actInt),"Patch");
	}
	
	public void buildAbout() {
		build(new AboutGUI(),"About");
	}
	
	public void buildShowSettings() {
		build(new ShowSettingsGUI(_console, _actInt), "Show Settings");
	}
	
	public void buildSettingsPrefs() {
		build(new SettingsPrefrencesGUI(_console, _actInt), "Cameo Prefrences");
	}
	
	public void buildSettingsClear() {
		build(new SettingsClearGUI(_console, _actInt), "Clear Functions");
	}
	
	public void quitApp() {
		_actInt.interprete(new ACTSaveShow());
		_actInt.interprete(new ACTSettingsPrefMod());
		System.exit(0);
	}
	
	
}
