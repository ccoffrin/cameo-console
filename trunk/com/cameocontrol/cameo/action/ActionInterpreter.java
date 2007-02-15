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

package com.cameocontrol.cameo.action;

import com.cameocontrol.cameo.control.Console;
import com.cameocontrol.cameo.control.SemInquery;
import com.cameocontrol.cameo.dataStructure.CommandLineMessage;
import com.cameocontrol.cameo.dataStructure.ErrorMessage;
import com.cameocontrol.cameo.output.OutputManager;
import com.cameocontrol.cameo.parser.cmdLexer;
import com.cameocontrol.cameo.parser.cmdParser;
import com.cameocontrol.cameo.resource.ResourceManager;

import antlr.RecognitionException;
import antlr.TokenStreamException;



/**
 * Eventually this class will package the erros generated by the Sem Visitor
 * and pass them back to the UI
 * @author carletoncoffrin
 *
 */
public class ActionInterpreter {
	//private BasicConsole _console;
	private SemVisitor _check;
	private ExeVisitor _execute;
	
	public ActionInterpreter(Console c, OutputManager om, ResourceManager rm, SemInquery ci) {
		//_console = c;
		_check = new SemVisitor(ci);
		_execute = new ExeVisitor(c, om, rm);
	}
	
	public CommandLineMessage interprete(ACTAction a){
		CommandLineMessage err = null;
		System.out.println(a);
		err = _check.check(a);
		if(err == null)
			_execute.doIt(a);
		return err;
	}
	
	public CommandLineMessage interprete(String s){
		ACTAction a = null;
		CommandLineMessage err = null;

		try {
			 cmdLexer lexer = new cmdLexer(parseStringToIS(s));
			 //lexer.customize();
			 cmdParser parser = new cmdParser(lexer);
			 a = parser.command();

		} catch(TokenStreamException e) {return new ErrorMessage(e.getMessage());} 
		catch(RecognitionException re) {return new ErrorMessage(re.getMessage());} 
	    //catch(Exception e) {System.out.println("!"+e); return(e.getMessage());}
		System.out.println(a);
		err = _check.check(a, s);
		if(err == null)
			_execute.doIt(a);
		System.out.println(err);
		return err;
	}
	
	/**
	 * the cmdLexer expects some kind of data stream, but the UI provides a string.  
	 * This method allows the string to be accessed like an input stream
	 * @param s the document to be parsed
	 * @return
	 */
	public java.io.InputStream parseStringToIS(String s){
		if(s==null) 
			return null;
		s = s.trim();
		java.io.InputStream in = null;
		try{
			in = new java.io.ByteArrayInputStream(s.getBytes("UTF-8"));
		}catch(Exception ex){
		}
		return in;
	}
}