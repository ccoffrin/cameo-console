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

import javax.swing.JTabbedPane;
import javax.swing.JComponent;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.Dimension;

import javax.swing.JPanel;

import com.cameocontrol.cameo.action.ActionInterpreter;
import com.cameocontrol.cameo.control.ConsoleInquiry;
import com.cameocontrol.cameo.control.ConsolePrefrences;
import com.cameocontrol.cameo.control.ConsoleSettings;
import com.cameocontrol.cameo.gui.binder.KeyBinder;
import com.cameocontrol.cameo.gui.binder.KeyBinderChannel;
import com.cameocontrol.cameo.gui.binder.KeyBinderCueList;
import com.cameocontrol.cameo.gui.binder.KeyBinderMagic;
import com.cameocontrol.cameo.gui.binder.KeyBinderSSheet;

public class CameoGUI extends JPanel {//implements ActionListener {

	private CommandLine _cmdLine;
	//private Update _updater;
	private ActionInterpreter _actInt;
	private ConsoleSettings _settings;
	private ConsolePrefrences _prefs;
	private KeyBinder _binder;
	
	public CameoGUI(ConsoleInquiry ci, ActionInterpreter ai)
	{
		//super(new GridLayout(1, 1));
		super(new BorderLayout());
		//_errMsg = false;
		//_actInt = new CMDLineInterpreter(c);
		_actInt = ai;
		_settings = ci.getSettings();
		_prefs = ci.getPrefrences();
		//_updater = new Update();
		_cmdLine = new CommandLine(_actInt);
		_binder = new KeyBinder(_cmdLine);
        JTabbedPane tabbedPane = new JTabbedPane();
        //Uncomment the following line to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        tabbedPane.setAutoscrolls(true);
       //ImageIcon icon = createImageIcon("images/middle.gif");

       
        JComponent panel4 = makeTextPanel("Group List");
        panel4.setPreferredSize(new Dimension(410, 50));
        tabbedPane.addTab("Group List", panel4);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_F4);
        
        
        //JGraphFactory fact =  new JGraphFactory(c);
        //JGraph channels = fact.buildJGraph(c.liveCue(), c.getSettings().getChannelsPerLine(), c.getSettings().getChannelGrouping(), c.getSettings().getLineGrouping()); 
 
        //JComponent panel1 = makeTextPanel("Channel");
        tabbedPane.addTab("Channel", new ChannelGUI(ci, _actInt, new KeyBinderChannel(_cmdLine, _prefs.getMouseMiddleAction())));
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_F1);

        //JComponent panel2 = makeTextPanel("Magic Sheet");
        tabbedPane.addTab("Magic Sheet", new MagicSheetGUI(ci, _actInt, _cmdLine, new KeyBinderMagic(_cmdLine, _prefs.getMouseMiddleAction())));
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_F2);
        
        //JComponent panel3 = makeTextPanel("Cue List");
        tabbedPane.addTab("Cue List", new CueListGUI(ci, _actInt, new KeyBinderCueList(_cmdLine)));
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_F3);

        
        //JComponent panel5 = makeTextPanel("Spread Sheet");
        //panel5.setPreferredSize(new Dimension(410, 50));
        
        tabbedPane.addTab("Spread Sheet", new SSheetGUI(ci, new KeyBinderSSheet(_cmdLine)));
        tabbedPane.setMnemonicAt(4, KeyEvent.VK_F5);
        
        /*
        tabbedPane.addTab("Patch", new PatchGUI(ci, _actInt));
        tabbedPane.setMnemonicAt(5, KeyEvent.VK_F6);
        */

        _binder.bind(this);
        //Add the tabbed pane to this panel.
        add(new ShowInfoGUI(ci), BorderLayout.PAGE_START);
        add(tabbedPane, BorderLayout.CENTER);
        add(_cmdLine, BorderLayout.PAGE_END);
    
    }
	
	//public CommandLine getCommandLine() {return _cmdLine;}
	//public GUIUpdater getUIUpdater() {return _updater;}

	private JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        _binder.bind(panel);
        return panel;
    }
}
