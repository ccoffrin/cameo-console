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

import java.io.BufferedWriter;
import java.io.IOException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class PrefrencesOutputContentHandler extends DefaultHandler {
	private BufferedWriter _output;
	private int _depth;
	private int _tabSize;
	public PrefrencesOutputContentHandler(BufferedWriter out){
		_output = out;
		_depth = 0;
		_tabSize = 3;
	}

	private String newLine(){
		StringBuffer s = new StringBuffer(64);
		s.append('\n');
		for(int k=0; k<_depth*_tabSize; k++)
			s.append(' ');
		return s.toString();
	}
	
	public void startElement(String uri, String localName, String qName, Attributes attributes)
	throws SAXException {
		try{_output.write(newLine()+"<"+ localName + ">");}
		catch(IOException io) {System.out.println(io);}
		_depth++;
	}
	
	public void characters(char[] ch, int start, int length)
	throws SAXException {
		String text = new String(ch, start, length); 
		try{_output.write(text);}
		catch(IOException io) {System.out.println(io);}
	}
	
	public void endElement(String uri, String localName, String qName)
	throws SAXException {
		_depth--; 
		try{_output.write("</"+localName+">");}
		catch(IOException io) {System.out.println(io);}
	}
	
}