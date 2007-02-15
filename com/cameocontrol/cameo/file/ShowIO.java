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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

import org.w3c.dom.*;


import org.xml.sax.SAXException;

import com.cameocontrol.cameo.action.ACTPatchChan;
import com.cameocontrol.cameo.control.CameoSettings;
import com.cameocontrol.cameo.control.CameoShow;
import com.cameocontrol.cameo.control.CameoCueList;
import com.cameocontrol.cameo.control.CameoMagicSheet;
import com.cameocontrol.cameo.control.ConsoleChannel;
import com.cameocontrol.cameo.control.ConsoleSettings;
import com.cameocontrol.cameo.control.ConsoleSettings.RecordMode;
import com.cameocontrol.cameo.control.ConsoleCue;
import com.cameocontrol.cameo.control.ConsoleFade;
import com.cameocontrol.cameo.control.ConsoleMagicSheet;
import com.cameocontrol.cameo.control.CameoFade;
import com.cameocontrol.cameo.dataStructure.Position;
import com.cameocontrol.cameo.file.adt.CueData;
import com.cameocontrol.cameo.file.adt.FadeData;
import com.cameocontrol.cameo.file.adt.MagicChannelData;
import com.cameocontrol.cameo.file.adt.SettingsData;
import com.cameocontrol.cameo.file.adt.ShowFileData;
import com.cameocontrol.cameo.file.adt.MagicSheetData;
import com.cameocontrol.cameo.file.adt.CueListData;
import com.cameocontrol.cameo.file.adt.PatchData;

import com.cameocontrol.cameo.output.CameoPatch;
import com.cameocontrol.cameo.output.Patch;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Vector;
import java.util.StringTokenizer;


public class ShowIO {
	private DocumentBuilderFactory _factory;
	  //factory.setNamespaceAware(true);
	private DocumentBuilder _builder;
	private DOMImplementation _impl;
	private Calendar _calendar;
	private String _dateFormat = "yyyyMMdd_HHmm";
	private SimpleDateFormat _format;
	
	private String _fileType = "CameoShow";
	private String _settings = "Settings";
	private String _patch = "Patch";
	private String _cues = "Cues";
	private String _cue = "Cue";
	private String _magicSheet = "MagicSheet";
	
	private String _recordMode = ConsoleSettings.RECORD_MODE_TAG;
	private String _totalChannels = ConsoleSettings.TOTAL_CHANNELS_TAG;
	private String _totalDimmers = ConsoleSettings.TOTAL_DIMMERS_TAG;
	private String _dUpTime = ConsoleSettings.DEFAULT_UP_TIME_TAG;
	private String _dDownTime = ConsoleSettings.DEFAULT_DOWN_TIME_TAG;
	private String _gotoCueTime = ConsoleSettings.DEFAULT_GOTOCUE_TIME_TAG;
	private String _title = ConsoleSettings.SHOW_TITLE_TAG;
	private String _comment = ConsoleSettings.SHOW_TITLE_TAG;
	private String _channelPerLine = ConsoleSettings.CHANNELS_PER_LINE_TAG;
	private String _channelHGroup = ConsoleSettings.CHANNELS_PER_HORIZONTEL_GROUP_TAG;
	private String _channelVGroup = ConsoleSettings.CHANNELS_PER_VERTICAL_GROUP_TAG;
	
	
	//TODO there is a collition here between ConsoleCue, ConsoleFade and Patch, should be resolved
	private String _channel = Patch.CHANNEL_TAG;
	private String _number = Patch.NUMBER_ATTRIB;
	
	
	private String _discription = ConsoleCue.DESCIPTION_TAG;
	private String _uptime = ConsoleFade.UP_TIME_ATTRIB;
	private String _downTime = ConsoleFade.DOWN_TIME_ATTRIB;
	private String _upTimeDelay = ConsoleFade.UP_TIME_DELAY_ATTRIB;
	private String _downTimeDelay = ConsoleFade.DOWN_TIME_ATTRIB;
	private String _followtime = ConsoleFade.FOLLOW_TIME_ATTRIB;
	private String _nextCue = ConsoleFade.NEXT_CUE_ATTRIB;
	
	private String _x = ConsoleMagicSheet.X_POSITION_ATTRIB;
	private String _y = ConsoleMagicSheet.Y_POSITION_ATTRIB;

	
	public ShowIO() {
	    /*
	    ** on some JDK, the default TimeZone is wrong
	    ** we must set the TimeZone manually!!!
	    **     sdf.setTimeZone(TimeZone.getTimeZone("EST"));
	    */
	    
		//_calendar = Calendar.getInstance(TimeZone.getDefault());
		_calendar = Calendar.getInstance();	 
		_format = new SimpleDateFormat(_dateFormat);          
	    //System.out.println("Now : " + _format.format(_calendar.getTime()));
		
		_factory = DocumentBuilderFactory.newInstance();
		try{_builder = _factory.newDocumentBuilder();}
		catch(ParserConfigurationException e) { System.out.println("Could not locate a JAXP DocumentBuilder class"); System.out.println(e);}
		_impl = _builder.getDOMImplementation();
	}
	
	public ShowFileData loadShow(String s) {
		Vector<Pair> links = new Vector<Pair>();
		/*
		ShowFileData show = new ShowFileData();
		show.setSettings(new CameoSettings());
		show.setPatch(new CameoPatch(0,0));
		show.setCueList(new CueList(0));
		show.setMagicSheet(new MagicSheet());
		*/
		SettingsData settingsData = new SettingsData();
		MagicSheetData magicSheetData = new MagicSheetData();
		CueListData cueListData = new CueListData();
		PatchData patchData = new PatchData();
		
		Document doc;
		try{
			doc = _builder.parse(s);
			
			Element root = doc.getDocumentElement();
			Element settings = getElement(root, _settings);
			Element patch = getElement(root, _patch);
			Element cues = getElement(root, _cues);
			Element magic = getElement(root, _magicSheet);
			//TODO test that all of these are non null and report incorrect file missing ... 
			
			
			String recordMode = getElement(settings, _recordMode).getTextContent();
			settingsData.put(_recordMode, recordMode);
			/*
			if(recordMode.equals(RecordMode.TRACKING.toString()))
				show._settings._mode = RecordMode.TRACKING;
			else if(recordMode.equals(RecordMode.CUE_ONLY.toString()))
				show._settings._mode = RecordMode.CUE_ONLY;
			*/
			settingsData.put(_totalChannels, getElement(settings, _totalChannels).getTextContent());
			settingsData.put(_totalDimmers, getElement(settings, _totalDimmers).getTextContent());
			settingsData.put(_dUpTime, getElement(settings, _dUpTime).getTextContent());
			settingsData.put(_dDownTime, getElement(settings, _dDownTime).getTextContent());
			settingsData.put(_gotoCueTime, getElement(settings, _gotoCueTime).getTextContent());
			settingsData.put(_title, getElement(settings, _title).getTextContent());
			settingsData.put(_comment, getElement(settings, _comment).getTextContent());
			settingsData.put(_channelPerLine, getElement(settings, _channelPerLine).getTextContent());
			settingsData.put(_channelHGroup, getElement(settings, _channelHGroup).getTextContent());
			settingsData.put(_channelVGroup, getElement(settings, _channelVGroup).getTextContent());
			
			/*
			show._settings._totalChannels = Integer.parseInt(getElement(settings, _totalChannels).getTextContent());
			show._settings._totalDimmers = Integer.parseInt(getElement(settings, _totalDimmers).getTextContent());
			show._settings._upTime = Integer.parseInt(getElement(settings, _dUpTime).getTextContent());
			show._settings._downTime = Integer.parseInt(getElement(settings, _dDownTime).getTextContent());
			show._settings._gotoCueTime = Integer.parseInt(getElement(settings, _gotoCueTime).getTextContent());
			show._settings._showTitle = getElement(settings, _title).getTextContent();
			show._settings._showComment = getElement(settings, _comment).getTextContent();
			
			show._settings._ChannelsPerLine = Integer.parseInt(getElement(settings, _channelPerLine).getTextContent());
			show._settings._ChannelGrouping = Integer.parseInt(getElement(settings, _channelHGroup).getTextContent());
			show._settings._LineGrouping = Integer.parseInt(getElement(settings, _channelVGroup).getTextContent());
			*/
			
			int totalChannels = Integer.parseInt(settingsData.get(_totalChannels));
			
			patchData.setTotalChannels(totalChannels);
			patchData.setTotalDimmers(Integer.parseInt(settingsData.get(_totalDimmers)));
			
			cueListData.setTotalChannels(totalChannels);
			
			//show.setPatch(new CameoPatch(show._settings._totalChannels, show._settings._totalDimmers));
			//show.setCueList(new CueList(show._settings._totalChannels));
			
			NodeList list = patch.getElementsByTagName(_channel);
			for(int x=0; x<list.getLength(); x++){
				Element el = (Element)list.item(x);
				int number = Integer.parseInt(el.getAttribute(_number));
				patchDims(patchData, number, el.getTextContent());
			}
			
			list = cues.getElementsByTagName(_cue);
			for(int x=0; x<list.getLength(); x++){
				Element el = (Element)list.item(x);
				CueData cue = new CueData();
				cue.setDescription(el.getAttribute(_discription));
				NodeList list2 = el.getElementsByTagName(_channel);
				for(int y=0; y<list2.getLength(); y++){
					Element chan = (Element)list2.item(y);
					setLevel(cue, chan.getAttribute(_number), chan.getTextContent());
				}
				
				int number = Integer.parseInt(el.getAttribute(_number));
				
				int upTime = Integer.parseInt(el.getAttribute(_uptime));
				int downTime = Integer.parseInt(el.getAttribute(_downTime));
				int delayUpTime = Integer.parseInt(el.getAttribute(_upTimeDelay));
				int delayDownTime = Integer.parseInt(el.getAttribute(_downTimeDelay));
				
				FadeData fade = new FadeData(number, upTime, downTime, delayUpTime, delayDownTime, cue);
				
				if(el.hasAttribute(_followtime))
					fade.setFollowTime(Integer.parseInt(el.getAttribute(_followtime)));
				
				if(el.hasAttribute(_nextCue))
					fade.setNextCue(Integer.parseInt(el.getAttribute(_nextCue)));
					
				cueListData.add(fade);
			}
			
			//TODO this needs to be done in the extractor
			//for(Pair p : links)
			//	show._cuelist.getCueNumbered(p._first).setNextCue(show._cuelist.getCueNumbered(p._second));
			
			list = magic.getElementsByTagName(_channel);
			for(int x=0; x<list.getLength(); x++){
				Element el = (Element)list.item(x);
				//Element xval = getElement(el, _x);
				//Element yval = getElement(el, _y);
				Integer number = new Integer(Integer.parseInt(el.getAttribute(_number))-1);
				double xpos = Double.parseDouble(el.getAttribute(_x));
				double ypos = Double.parseDouble(el.getAttribute(_y));
				magicSheetData.put(number, new MagicChannelData(xpos, ypos));
			}
			
		}
		catch (DOMException e) {
			System.err.println(e); 
		}
		catch (SAXException e) {
			System.err.println(e); 
		}
		catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(new JFrame(""), "The show file "+s+" was not found, loading default show.");
		}
		catch (IOException e) {
			System.err.println(e); 
		}
		
		return new ShowFileData(settingsData, cueListData, patchData, magicSheetData);
	}
	
	public String saveShow(String fn, ShowFileData showData) {
		_calendar = new GregorianCalendar();
		String showName = showData.getSettings().get(_title)+"_"+_format.format(_calendar.getTime())+".show";

		try{
	      new File(fn).mkdirs();
		  FileWriter _fileWriter = new FileWriter(fn+"/"+showName);
		  BufferedWriter _outputFile = new BufferedWriter(_fileWriter);
		  
		  // Create the document
		  Document doc = _impl.createDocument(null,_fileType, null);
		
		  Element root = doc.getDocumentElement();
		
		  Element settings = doc.createElement(_settings);
		  Element patch = doc.createElement(_patch);
		  Element cues = doc.createElement(_cues);
		  Element magic = doc.createElement(_magicSheet);
		  
		  addText(doc, settings, _recordMode, showData.getSettings().get(_recordMode));
		  addText(doc, settings, _totalChannels, showData.getSettings().get(_totalChannels));
		  addText(doc, settings, _totalDimmers, showData.getSettings().get(_totalDimmers));
		  addText(doc, settings, _dUpTime, showData.getSettings().get(_dUpTime));
		  addText(doc, settings, _dDownTime, showData.getSettings().get(_dDownTime));
		  addText(doc, settings, _gotoCueTime, showData.getSettings().get(_gotoCueTime));
		  addText(doc, settings, _title, showData.getSettings().get(_title));
		  addText(doc, settings, _comment, showData.getSettings().get(_comment));
		  
		  addText(doc, settings, _channelPerLine, showData.getSettings().get(_channelPerLine));
		  addText(doc, settings, _channelHGroup, showData.getSettings().get(_channelHGroup));
		  addText(doc, settings, _channelVGroup, showData.getSettings().get(_channelVGroup));

		  for(Integer chan : showData.getPatch().keySet())
			  if(showData.getPatch().get(chan).size() > 0)
				  addChannel(doc, patch, chan, showData.getPatch().get(chan));
		  
		  for(FadeData fade : showData.getCueList())
			  addFade(doc, cues, fade);
		  
		  Position p;
		  for(Integer chan : showData.getMagicSheet().keySet()){
			  MagicChannelData mChan = showData.getMagicSheet().get(chan);
			  
			  if(!(mChan._x == 0 && mChan._x == 0))
				  addMagicChannel(doc, magic, chan, mChan._x, mChan._y);
		  }
		  

		  root.appendChild(settings);
		  root.appendChild(patch);
		  root.appendChild(cues);
		  root.appendChild(magic);
		  
		  // Serialize the document onto System.out
		  TransformerFactory xformFactory = TransformerFactory.newInstance();  
		  Transformer idTransform = xformFactory.newTransformer();
		  Source input = new DOMSource(doc);
		 // Result output = new StreamResult(System.out);
		  //idTransform.transform(input, output);
		  Result output = new StreamResult(_outputFile);
		  idTransform.transform(input, output);
		  
		  _outputFile.close();
  		  _fileWriter.close();
		}
		catch (FactoryConfigurationError e) { 
		  System.out.println("Could not locate a JAXP factory class"); 
		}
		catch (DOMException e) {
		  System.err.println(e); 
		}
		catch (TransformerConfigurationException e) {
		  System.err.println(e); 
		}
		catch (TransformerException e) {
		  System.err.println(e); 
		}
		catch (IOException e) {
		  System.err.println(e); 
		}
		
		return showName;
	}
	
	private class Pair{
		int _first;
		int _second;
		
		public Pair(int x, int y){
			_first=x;
			_second=y;
		}
	}
	
	private void patchDims(PatchData p, int channel, String s) {
		StringTokenizer dims = new StringTokenizer(s, " ");
		while(dims.hasMoreTokens())
			p.patch(channel-1, Integer.parseInt(dims.nextToken()));
	}
	
	private void setLevel(CueData c, String channel, String level) {
			c.addLevel(Integer.parseInt(channel)-1, Short.parseShort(level));
	}
	
	private Element getElement(Element root, String name){
		NodeList list = root.getElementsByTagName(name);
		Element el = (Element)list.item(0);
		return el;
	}
	
	private void addText(Document doc, Element root, String name, String value){
		  Element el = doc.createElement(name);
		  Text txt = doc.createTextNode(value);
		  el.appendChild(txt);
		  root.appendChild(el);
	}
	
	private void addChannel(Document doc, Element root, Integer channel, HashSet<Integer> values){
		  Element el = doc.createElement(_channel);
		  Attr attr = doc.createAttribute(_number);
		  attr.setValue(Integer.toString(channel+1));
		  el.setAttributeNode(attr);
		  Text txt;
		  for(Integer x : values) {
			  txt = doc.createTextNode(x.toString());
			  el.appendChild(txt);
		  }
		  
		  root.appendChild(el);
	}
	private void addChannel(Document doc, Element root, int channel, short value){
		HashSet<Integer> level = new HashSet<Integer>();
		level.add(new Integer(value));
		addChannel(doc, root, channel, level);
	}
	
	private void addMagicChannel(Document doc, Element root, int channel, double x, double y){
		  Element el = doc.createElement(_channel);
		  //Element xel = doc.createElement(_x);
		  //Element yel = doc.createElement(_y);
		  Attr attr = doc.createAttribute(_number);
		  attr.setValue(Integer.toString(channel+1));
		  el.setAttributeNode(attr);
		  
		  attr = doc.createAttribute(_x);
		  attr.setValue(Double.toString(x));
		  el.setAttributeNode(attr);
		  
		  attr = doc.createAttribute(_y);
		  attr.setValue(Double.toString(y));
		  el.setAttributeNode(attr);

		  //xel.appendChild(doc.createTextNode(Integer.toString(x)));
		  //yel.appendChild(doc.createTextNode(Integer.toString(y)));
		  
		  //el.appendChild(xel);
		  //el.appendChild(yel);
		  root.appendChild(el);
	}
	
	private void addFade(Document doc, Element root, FadeData fade){
		Element el = doc.createElement(_cue);
		Attr attr;
		
		
		attr = doc.createAttribute(_number);
		attr.setValue(Integer.toString(fade.getCueNumber()));
		el.setAttributeNode(attr);
		
		attr = doc.createAttribute(_uptime);
		attr.setValue(Integer.toString(fade.getUpTime()));
		el.setAttributeNode(attr);
		
		attr = doc.createAttribute(_downTime);
		attr.setValue(Integer.toString(fade.getDownTime()));
		el.setAttributeNode(attr);
		
		attr = doc.createAttribute(_upTimeDelay);
		attr.setValue(Integer.toString(fade.getDelayUpTime()));
		el.setAttributeNode(attr);
		
		attr = doc.createAttribute(_downTimeDelay);
		attr.setValue(Integer.toString(fade.getDelayDownTime()));
		el.setAttributeNode(attr);
		
		if(fade.getFollowTime() >= 0){
			attr = doc.createAttribute(_followtime);
			attr.setValue(Integer.toString(fade.getFollowTime()));
			el.setAttributeNode(attr);
		}
		
		if(fade.getNextCue() > 0){
			attr = doc.createAttribute(_nextCue);
			attr.setValue(Integer.toString(fade.getNextCue()));
			el.setAttributeNode(attr);
		}
		
		CueData cue = fade.getCue();
		
		attr = doc.createAttribute(_discription);
		attr.setValue(cue.getDescription());
		el.setAttributeNode(attr);
		
		for(Integer chan : cue.keySet()) 
			addChannel(doc, el, chan, cue.get(chan));
		  
		root.appendChild(el);
	}
}
