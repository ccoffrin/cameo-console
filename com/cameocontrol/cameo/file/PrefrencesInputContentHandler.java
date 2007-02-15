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

package com.cameocontrol.cameo.file;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;

import java.io.BufferedWriter;

import java.util.Stack;
import java.util.HashMap;

public class PrefrencesInputContentHandler extends DefaultHandler {
	private HashMap<String,String> _data;
	private Stack<String> _stack;
	
	public PrefrencesInputContentHandler(){
		_data = new HashMap<String,String>();
		_stack = new Stack<String>();
	}
	
	public HashMap<String,String> getData() {return _data;}
	
	public void startElement(String uri, String localName, String qName, Attributes attributes)
	throws SAXException {
		_stack.push(localName);
		_data.put(localName, "");
	}
	
	public void characters(char[] ch, int start, int length)
	throws SAXException {
		String text = new String(ch, start, length); //System.out.println(text);
		String currentTag = _stack.peek();
		_data.put(_stack.peek(), text);
	}
	
	public void endElement(String uri, String localName, String qName)
	throws SAXException {_stack.pop();}
	
}
