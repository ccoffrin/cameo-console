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


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.cameocontrol.cameo.action.ActionInterpreter;
import com.cameocontrol.cameo.control.ConsoleInquiry;
import com.cameocontrol.cameo.control.ConsoleSettings;
import com.cameocontrol.cameo.gui.binder.KeyBinder;
import com.cameocontrol.cameo.gui.binder.KeyBinderChannel;
import com.cameocontrol.cameo.gui.binder.KeyBinderCueList;
import com.cameocontrol.cameo.gui.binder.KeyBinderMagic;
import com.cameocontrol.cameo.gui.binder.KeyBinderSSheet;


public class ShowSettingsGUI extends JPanel {
	private ActionInterpreter _actInt;
	private ConsoleInquiry _console;
	private KeyBinder _binder;
	
	public ShowSettingsGUI(ConsoleInquiry ci, ActionInterpreter ai)
	{
		super(new BorderLayout());
		_actInt = ai;
		_console = ci;
		//_binder = new KeyBinder(_cmdLine);
        JTabbedPane tabbedPane = new JTabbedPane();
        //Uncomment the following line to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        tabbedPane.setAutoscrolls(true);
      
       //ImageIcon icon = createImageIcon("images/middle.gif");

       
    
        
        //JGraphFactory fact =  new JGraphFactory(c);
        //JGraph channels = fact.buildJGraph(c.liveCue(), c.getSettings().getChannelsPerLine(), c.getSettings().getChannelGrouping(), c.getSettings().getLineGrouping()); 
 
        //JComponent panel1 = makeTextPanel("Channel");
        tabbedPane.addTab("General", new SettingsShowGeneralGUI(_console, _actInt));

        tabbedPane.addTab("Channels Layout", new SettingsShowChannelGUI(_console, _actInt));
        
        JComponent panel5 = makeTextPanel("Magic Sheet Settings");
        //panel5.setPreferredSize(new Dimension(100, 50));
        tabbedPane.addTab("Magic Sheet", panel5);
        
        //_binder.bind(this);
        //Add the tabbed pane to this panel.
        add(tabbedPane, BorderLayout.CENTER);
        
       // setSize((int)Toolkit.getDefaultToolkit().getScreenSize().height/2, Toolkit.getDefaultToolkit().getScreenSize().width/2);
        
    }

	private JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        //_binder.bind(panel);
        return panel;
    }
}
