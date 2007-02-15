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
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.xml.sax.ContentHandler;
import org.xml.sax.XMLReader;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.XMLReaderFactory;

import com.cameocontrol.cameo.control.ConsolePrefrences;
import com.cameocontrol.cameo.file.adt.PrefrencesData;

public class PrefrencesIO {
	private ContentHandler _handler;
	private XMLReader _parser;
	private String _fileName;
	
	private String prefrences = "Prefrences";
	private String startCodeTag = ConsolePrefrences.START_CODE_TAG;
	private String refreshRateTag = ConsolePrefrences.OUTOUT_REFRESH_TAG;
	private String mouseMiddleActionTag = ConsolePrefrences.MOUSE_MIDDLE_ACTION_TAG;
	private String showPath = ConsolePrefrences.SHOW_PATH_TAG;
	private String lastShowFile = ConsolePrefrences.LAST_SAVED_FILE_NAME_TAG;
	
	public PrefrencesIO() {
		_fileName = ".cameoPrefrences";
		
		try {_parser = XMLReaderFactory.createXMLReader();}
		catch (Exception e) {System.out.println(e);}
	}
	
	public PrefrencesData loadPrefrences() {
		HashMap<String,String> data;
		PrefrencesData prefs = new PrefrencesData();
		PrefrencesInputContentHandler handler = new PrefrencesInputContentHandler();
		_parser.setContentHandler(handler);
		
		try{  
			_parser.parse(_fileName);
			data = handler.getData();
			
			prefs.putAll(data);
		}
		catch (FileNotFoundException e) {
			savePrefrences(prefs);
			JOptionPane.showMessageDialog(new JFrame("Cameo"), "The Cameo prefrences file was not found, loading default Cameo settings.");
		} catch(Exception e){
			System.out.println(e.toString());
		}
		
		return prefs;
	}
	
	public void savePrefrences(PrefrencesData pd) {
		FileWriter _fileWriter;
		 try {  
    		_fileWriter = new FileWriter(_fileName);
    		BufferedWriter _outputFile = new BufferedWriter(_fileWriter);
    		//TODO write output file
    		
    		_handler = new PrefrencesOutputContentHandler(_outputFile);
    		_parser.setContentHandler(_handler);
    		
    		open(prefrences);
    			tagText(startCodeTag, pd);
    			tagText(refreshRateTag,pd);
    			tagText(mouseMiddleActionTag,pd);
    			tagText(showPath,pd);
    			tagText(lastShowFile,pd);
    		close(prefrences);
    		
    		_outputFile.close();
    		_fileWriter.close();
        } catch( Exception e ){
			System.out.println(e.toString());
		}	
	}
	
	private void open(String tag) throws SAXException {
		_handler.startElement("", tag, tag, new AttributesImpl());
	}
	
	private void close(String tag) throws SAXException {
		_handler.endElement("", tag, tag);
	}
	
	private void tagText(String key, PrefrencesData pd) throws SAXException {
		if(pd.containsKey(key)){
			assert(pd.get(key) != null):"key values should never be NULL";
			assert(!pd.get(key).equals("")):"key values should never be empy";
			String value = pd.get(key);
			
			open(key);
			_handler.characters(value.toCharArray(), 0, value.length());
			close(key);
		}
	}
}
