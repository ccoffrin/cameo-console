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
//import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import com.cameocontrol.cameo.gui.CommandLine;

public class KeyBinder {
	//private JComponent _panel;
	protected CommandLine _cmdLine;
	private boolean _lastNum;
	public KeyBinder(CommandLine c){
		
		_cmdLine = c;
		_lastNum = false;
		//_cmdLine.ac
	}
	
	public void bind(JComponent component) {
		component.addMouseListener(new ExecuteListener(_cmdLine));
		
		component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), enter);
		component.getActionMap().put(enter, new ExeAction());
	       
        component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "del");
        component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), "del");
        component. getActionMap().put("del", new DelAction());
	       
        component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_2, KeyEvent.SHIFT_DOWN_MASK), a);
		component.getInputMap().put(KeyStroke.getKeyStroke("A"), a);
		component.getActionMap().put(a, new aAction());
		
		component.getInputMap().put(KeyStroke.getKeyStroke("C"), c);
		component.getActionMap().put(c, new cAction());
	     
		component.getInputMap().put(KeyStroke.getKeyStroke("D"), d);
		component.getActionMap().put(d, new dAction());
		
		component.getInputMap().put(KeyStroke.getKeyStroke("E"), e);
		component.getActionMap().put(e, new eAction());
		
		component.getInputMap().put(KeyStroke.getKeyStroke("F"), f);
		component.getActionMap().put(f, new fAction());
		
		component.getInputMap().put(KeyStroke.getKeyStroke("G"), g);
		component.getActionMap().put(g, new gAction());
		
		component.getInputMap().put(KeyStroke.getKeyStroke("L"), l);
		component.getActionMap().put(l, new lAction());
		
		component.getInputMap().put(KeyStroke.getKeyStroke("O"), o);
		component.getActionMap().put(o, new oAction());
		
		component.getInputMap().put(KeyStroke.getKeyStroke("Q"), q);
		component.getActionMap().put(q, new qAction());
		
		component.getInputMap().put(KeyStroke.getKeyStroke("R"), r);
		component.getActionMap().put(r, new rAction());
		
		component.getInputMap().put(KeyStroke.getKeyStroke("S"), s);
		component.getActionMap().put(s, new sAction());
		
		component.getInputMap().put(KeyStroke.getKeyStroke("T"), t);
		component.getActionMap().put(t, new tAction());
		
		component.getInputMap().put(KeyStroke.getKeyStroke("U"), u);
		component.getActionMap().put(u, new uAction());
		
		component.getInputMap().put(KeyStroke.getKeyStroke("W"), w);
		component.getActionMap().put(w, new wAction());
		
		component.getInputMap().put(KeyStroke.getKeyStroke("X"), x);
		component.getActionMap().put(x, new xAction());
		
		
		
		
		component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SLASH, KeyEvent.CTRL_DOWN_MASK), bk);
		component.getActionMap().put(bk, new bkAction());
		
		component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SLASH, KeyEvent.SHIFT_DOWN_MASK), go);
		component.getActionMap().put(go, new goAction());
		
		component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_PERIOD, 0), period);
		component.getActionMap().put(period, new periodAction());
		
		component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SLASH, 0), slash);
		component.getActionMap().put(slash, new slashAction());
		
		//TODO remove this when plus is working
		//_panel.getInputMap().put(KeyStroke.getKeyStroke("P"), plus);
		component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, KeyEvent.SHIFT_DOWN_MASK), plus);
		component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, 0), plus);
		component.getActionMap().put(plus, new plusAction());
		
		component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_QUOTE, 0), plust);
		component.getActionMap().put(plust, new plustAction());
		
		component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, 0), minus);
		component.getActionMap().put(minus, new minusAction());
		
		component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SEMICOLON, 0), minust);
		component.getActionMap().put(minust, new minustAction());
		
		component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_8, KeyEvent.SHIFT_DOWN_MASK), times);
		component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_MULTIPLY, 0), times);
		component.getActionMap().put(times, new timesAction());
		
		component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_OPEN_BRACKET, 0), prev);
		component.getActionMap().put(prev, new prevAction());
		
		component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_CLOSE_BRACKET, 0), next);
		component.getActionMap().put(next, new nextAction());
		
		
		component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), space);
		component.getActionMap().put(space, new spaceAction());
		
		component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), upArrow);
		component.getActionMap().put(upArrow, new upArrowAction());
		
		
		component.getInputMap().put(KeyStroke.getKeyStroke("0"), zero);
		component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD0, 0), zero);
		component.getActionMap().put(zero, new zeroAction());
		
		component.getInputMap().put(KeyStroke.getKeyStroke("1"), one);
		component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD1, 0), one);
		component.getActionMap().put(one, new oneAction());
		
		component.getInputMap().put(KeyStroke.getKeyStroke("2"), two);
		component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD2, 0), two);
		component.getActionMap().put(two, new twoAction());
		
		component.getInputMap().put(KeyStroke.getKeyStroke("3"), three);
		component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD3, 0), three);
		component.getActionMap().put(three, new threeAction());
		
		component.getInputMap().put(KeyStroke.getKeyStroke("4"), four);
		component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD4, 0), four);
		component.getActionMap().put(four, new fourAction());
		
		component.getInputMap().put(KeyStroke.getKeyStroke("5"), five);
		component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD5, 0), five);
		component.getActionMap().put(five, new fiveAction());
		
		component.getInputMap().put(KeyStroke.getKeyStroke("6"), six);
		component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD6, 0), six);
		component.getActionMap().put(six, new sixAction());
		
		component.getInputMap().put(KeyStroke.getKeyStroke("7"), seven);
		component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD7, 0), seven);
		component.getActionMap().put(seven, new sevenAction());
		
		component.getInputMap().put(KeyStroke.getKeyStroke("8"), eight);
		component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD8, 0), eight);
		component.getActionMap().put(eight, new eightAction());
		
		component.getInputMap().put(KeyStroke.getKeyStroke("9"), nine);
		component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD9, 0), nine);
		component.getActionMap().put(nine, new nineAction());
		
		component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ALL_CANDIDATES, KeyEvent.BUTTON2_DOWN_MASK), enter);
		
	}
	
	private String a = "@";
	private String c = "channel";
	private String d = "dimmer";
	private String e = "delete cue";
	private String f = "@ full";
	private String g = "goto cue";
	private String l = "load cue";
	private String o = "@ out";
	private String q = "cue";
	private String r = "record cue";
	private String s = "follow";
	private String t = "time";
	private String u = "group";
	private String w = "delay";
	private String x = "link";
	
	private String slash = "/";
	private String plus = "+";
	private String plust = "@ +10";
	private String minus = "-";
	private String minust = "@ -10";
	private String times = "->";
	private String period = ".";
	private String bk = "back";
	private String go = "go";
	private String next = "next";
	private String prev = "previous";
	private String track = "track";
	private String cueonly = "cueonly";
	
	private String space = "";
	
	private String zero = "0";
	private String one = "1";
	private String two = "2";
	private String three = "3";
	private String four = "4";
	private String five = "5";
	private String six = "6";
	private String seven = "7";
	private String eight = "8";
	private String nine = "9";
	
	private String upArrow = "";
	
	protected String enter = "execute";
	
	protected void append(String s) {
		if(_lastNum) {
			_cmdLine.setText(_cmdLine.getText()+" "+s+" ");
			_lastNum = false;
		}
		else {
		_cmdLine.setText(_cmdLine.getText()+s+" ");
		}
	}	
	
	protected void appendNum(String s) {
		_cmdLine.setText(_cmdLine.getText() +s);
		_lastNum = true;
	}
	
	private class aAction extends AbstractAction {public void actionPerformed(ActionEvent e) {append(a);}}
	private class cAction extends AbstractAction {public void actionPerformed(ActionEvent e) {append(c);}}
	private class dAction extends AbstractAction {public void actionPerformed(ActionEvent e) {append(d);}}
	private class eAction extends AbstractAction {public void actionPerformed(ActionEvent event) {append(e);}}
	private class fAction extends AbstractAction {public void actionPerformed(ActionEvent e) {append(f);}}
	private class gAction extends AbstractAction {public void actionPerformed(ActionEvent e) {append(g);}}
	private class lAction extends AbstractAction {public void actionPerformed(ActionEvent e) {append(l);}}
	private class oAction extends AbstractAction {public void actionPerformed(ActionEvent e) {append(o);}}
	private class qAction extends AbstractAction {public void actionPerformed(ActionEvent e) {append(q);}}
	private class rAction extends AbstractAction {public void actionPerformed(ActionEvent e) {append(r);}}
	private class sAction extends AbstractAction {public void actionPerformed(ActionEvent e) {append(s);}}
	private class tAction extends AbstractAction {public void actionPerformed(ActionEvent e) {append(t);}}
	private class uAction extends AbstractAction {public void actionPerformed(ActionEvent e) {append(u);}}
	private class wAction extends AbstractAction {public void actionPerformed(ActionEvent e) {append(w);}}
	private class xAction extends AbstractAction {public void actionPerformed(ActionEvent e) {append(x);}}
	
	//post action event causes the command line to be exicuated as soon as the the key is typed
	private class bkAction extends AbstractAction {public void actionPerformed(ActionEvent e) {append(bk); _cmdLine.execute();}}
	private class goAction extends AbstractAction {public void actionPerformed(ActionEvent e) {append(go); _cmdLine.execute();}}
	private class plustAction extends AbstractAction {public void actionPerformed(ActionEvent e) {append(plust); _cmdLine.execute();}}
	private class minustAction extends AbstractAction {public void actionPerformed(ActionEvent e) {append(minust); _cmdLine.execute();}}
	private class nextAction extends AbstractAction {public void actionPerformed(ActionEvent e) {append(next); _cmdLine.execute();}}
	private class prevAction extends AbstractAction {public void actionPerformed(ActionEvent e) {append(prev); _cmdLine.execute();}}
	
	private class slashAction extends AbstractAction {public void actionPerformed(ActionEvent e) {append(slash);}}
	private class plusAction extends AbstractAction {public void actionPerformed(ActionEvent e) {append(plus);}}
	private class minusAction extends AbstractAction {public void actionPerformed(ActionEvent e) {append(minus);}}
	private class timesAction extends AbstractAction {public void actionPerformed(ActionEvent e) {append(times);}}
	private class spaceAction extends AbstractAction {public void actionPerformed(ActionEvent e) {append(space);}}
	
	private class upArrowAction extends AbstractAction {public void actionPerformed(ActionEvent e) {_cmdLine.setLastCommand();}}

	private class zeroAction extends AbstractAction {public void actionPerformed(ActionEvent e) {appendNum(zero);}}
	private class oneAction extends AbstractAction {public void actionPerformed(ActionEvent e) {appendNum(one);}}
	private class twoAction extends AbstractAction {public void actionPerformed(ActionEvent e) {appendNum(two);}}
	private class threeAction extends AbstractAction {public void actionPerformed(ActionEvent e) {appendNum(three);}}
	private class fourAction extends AbstractAction {public void actionPerformed(ActionEvent e) {appendNum(four);}}
	private class fiveAction extends AbstractAction {public void actionPerformed(ActionEvent e) {appendNum(five);}}
	private class sixAction extends AbstractAction {public void actionPerformed(ActionEvent e) {appendNum(six);}}
	private class sevenAction extends AbstractAction {public void actionPerformed(ActionEvent e) {appendNum(seven);}}
	private class eightAction extends AbstractAction {public void actionPerformed(ActionEvent e) {appendNum(eight);}}
	private class nineAction extends AbstractAction {public void actionPerformed(ActionEvent e) {appendNum(nine);}}
	private class periodAction extends AbstractAction {public void actionPerformed(ActionEvent e) {appendNum(period);}}
	
	private class ExeAction extends AbstractAction {public void actionPerformed(ActionEvent e) {_cmdLine.execute();}}
	
	private class DelAction extends AbstractAction {public void actionPerformed(ActionEvent e) {_cmdLine.delete();}}
	
}
