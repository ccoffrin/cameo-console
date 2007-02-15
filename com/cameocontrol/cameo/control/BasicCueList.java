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

package com.cameocontrol.cameo.control;

import java.util.LinkedList;
import java.util.Iterator;
import java.util.HashSet;
import java.util.ListIterator;

import com.cameocontrol.cameo.file.adt.FadeData;
import com.cameocontrol.cameo.file.adt.CueListData;

/// List of cue tranistions with a pointer to the current cue

//What are the implications if this class actully extends LinkedList?
//maybe not becouse of the speshalized nature of the adda nd remove?

		
public abstract class BasicCueList implements ConsoleCueList {
	protected LinkedList<ConsoleFade> _cues;
	protected ConsoleFade _currentCue;
	
	public BasicCueList(){
		_cues = new LinkedList<ConsoleFade>();
	}
		
	public void clearCues(){_cues = new LinkedList<ConsoleFade>();}
	
	public void recordTracking(ConsoleFade newCue) {addTracking(newCue, false);}
	public void updateTracking(ConsoleFade newCue) {addTracking(newCue, true);}
	
	public void addTracking(ConsoleFade newCue, boolean merge)
	{
		HashSet<TrackingChannel> trackChans;
		if(_cues.isEmpty())
			_cues.add(newCue);
		else if(isLast(newCue)){
			if(_cues.contains(newCue)) //if the cue is not already in the list
				_cues.set(insertIndex(newCue.getNumber()),newCue);	
			else
				_cues.add(insertIndex(newCue.getNumber()),newCue);	
		}
		else{
			int index = insertIndex(newCue.getNumber());
			ConsoleFade mergedCue; 
			
			if(merge)
				mergedCue = mergeCues(_cues.get(index),newCue);
			else
				mergedCue = newCue;
			
			if(_cues.contains(newCue)) {//if the cue is not already in the list
				
				trackChans = findTrackingChans(_cues.get(index).getCue(), mergedCue.getCue());
				_cues.set(index, mergedCue);	
			}
			else {
				
				trackChans = findTrackingChans(_cues.get(index).getCue(),_cues.get(index+1).getCue(), mergedCue.getCue());
				_cues.add(index,mergeCues(_cues.get(index),mergedCue));	
			}
			track(trackChans,_cues.indexOf(newCue)+1);
		}
		_currentCue = newCue;
	}
	
	private ConsoleFade mergeCues(ConsoleFade oCue, ConsoleFade nCue){
		
		for(Integer chan : oCue.getCue())
			if(oCue.getCue().getLevel(chan) == ConsoleChannel.OUT)
				nCue.getCue().setLevel(chan, oCue.getCue().getLevel(chan));
		
		return nCue;
	}
	
	private boolean isLast(ConsoleFade c) {
		return _cues.getLast().equals(c);
	}
	
	private boolean isFirst(ConsoleFade c) {
		return _cues.getFirst().equals(c);
	}
	
	private void track(HashSet<TrackingChannel> chans, int cueIndex) {
		int index = cueIndex;
		HashSet<TrackingChannel> nextChans = chans;
		ConsoleCue cue;
		while(!nextChans.isEmpty() && index < _cues.size()){
			cue = _cues.get(index).getCue();
			chans = nextChans;
			nextChans = new HashSet<TrackingChannel>();
			for(TrackingChannel tc : chans){
				if(cue.getLevel(tc._channel) == tc._oldLevel || cue.getLevel(tc._channel) == -1){
					cue.setLevel(tc._channel, tc._newLevel);
					nextChans.add(tc);
				}
			}
			index++;
		}
	}
	
	private HashSet<TrackingChannel> findTrackingChans(ConsoleCue firstCue, ConsoleCue secondCue, ConsoleCue newCue) {
		HashSet<TrackingChannel> chans = new HashSet<TrackingChannel>();
		int highest =  firstCue.getHighestChannel();
		if(highest < secondCue.getHighestChannel())
			highest = secondCue.getHighestChannel();
		if(highest < newCue.getHighestChannel())
			highest = newCue.getHighestChannel();
		
		for(int x=0; x < highest; x++){
			if(((firstCue.getLevel(x) == secondCue.getLevel(x)) && (firstCue.getLevel(x) != newCue.getLevel(x))))
				chans.add(new TrackingChannel(x, firstCue.getLevel(x), newCue.getLevel(x)));
		}
		return chans;
	}
	
	private HashSet<TrackingChannel> findTrackingChans(ConsoleCue firstCue, ConsoleCue newCue) {
		HashSet<TrackingChannel> chans = new HashSet<TrackingChannel>();
		int highest =  firstCue.getHighestChannel();
		if(highest < newCue.getHighestChannel())
			highest = newCue.getHighestChannel();
		
		for(int x=0; x < highest; x++){
			if(firstCue.getLevel(x) != newCue.getLevel(x))
				chans.add(new TrackingChannel(x, firstCue.getLevel(x), newCue.getLevel(x)));
		}
		return chans;
	}
	
	public void addCueOnly(ConsoleFade c)
	{
		if(_cues.isEmpty())
			_cues.add(c);
		else{
			if(_cues.contains(c)) //if the cue is not already in the list
				_cues.set(insertIndex(c.getNumber()),c);	
			else
				_cues.add(insertIndex(c.getNumber()),c);	
		}
		_currentCue = c;
	}
	
	//TODO: impliment this
	public void removeTracking(ConsoleFade c) {
		if(_currentCue.equals(c))
			advanceCurrentCue(c);
		
		if(!isLast(c)){
			HashSet<TrackingChannel> trackChans;
			ConsoleFade previous;
			if(isFirst(c))
				previous = c;
			else
				previous = _cues.listIterator(_cues.indexOf(c)).previous();
			
			trackChans = findTrackingChans(previous.getCue(), c.getCue());
			//_cues.set(previous, c);
			
			for(TrackingChannel tChan : trackChans){
				tChan._oldLevel = tChan._newLevel;
				tChan._newLevel = ConsoleChannel.OUT;
			}
			
			track(trackChans,_cues.indexOf(c)+1);
		}

		_cues.remove(c);
	}
	
	public void removeCueOnly(ConsoleFade c){
		if(_currentCue.equals(c))
			advanceCurrentCue(c);
		
		_cues.remove(c);
	}
	
	private void advanceCurrentCue(ConsoleFade c){
		ListIterator<ConsoleFade> list = _cues.listIterator(_cues.indexOf(c));
		
		if(isFirst(c))
			_currentCue = list.next();
		else
			_currentCue = list.previous();
	}
	
	public boolean isCurrentCue(ConsoleFade c) {return _currentCue == c;}
	
	public ConsoleFade getCurrentCue() {return _currentCue;}
	public void setCurrentCue(ConsoleFade c) {_currentCue = c;}
	
	/*
	 * Inserts a cue in the correct place give a cue Number  
	 */
	private int insertIndex(int newCueNumber)
	{
		Iterator<ConsoleFade> cues = _cues.iterator();
		ConsoleFade temp;// = cueList.getFirst();
		//CueTransition cue = cueList.getFirst();
		
		while(cues.hasNext())
		{
			temp = cues.next();
			//if(temp.getNumber() == newCueNumber)
			//	return -1;
			if(temp.getNumber() >= newCueNumber)
				return _cues.indexOf(temp);
		}
		
		return _cues.indexOf(_cues.getLast())+1;
	}

	
	//TODO possibly add a hashtable to track the number of all the cues in the list for quick look up by cue number?
	public ConsoleFade getCueNumbered(int x)
	{
		Iterator<ConsoleFade> cues = _cues.iterator();
		//CueTransition cue = cueList.getFirst();
		if(x <= 0)
			return new CameoFade(0, new CameoTime(1500,1500,0,0), new CameoCue());
		else {
			while(cues.hasNext()){
				ConsoleFade temp = cues.next();
				if(temp.getNumber() == x)
					return temp;
				if(temp.getNumber() > x)
					return null;
			}
			
			return null;
		}
	}
	
	public int size() {return _cues.size();}
	
	/*
	public ConsoleFade getCueIndexed(int x){
		return _cues.get(x);
	}
	*/
	
	public int getNextCueNumber() {
		return getNextCue().getNumber();
	}
	
	public ConsoleFade getNextCue(){
		if(_currentCue.getNextCue() == null){
			if(isLast(_currentCue))
				return _currentCue;
			else {
				if(_currentCue.getNumber() == 0){
					return _cues.getFirst();
				}
				else {
					int index = _cues.indexOf(_currentCue);
					ListIterator<ConsoleFade> list = _cues.listIterator(index);
					list.next(); //this is the cue of index
					return list.next();
				}
			}
		}
		else
			return _currentCue.getNextCue();
	}
	
	public ConsoleFade getPrevCue() {
		if(!isFirst(_currentCue)){
			ListIterator<ConsoleFade> list = _cues.listIterator(_cues.indexOf(_currentCue));
			return list.previous();
		}
		else
			return new CameoFade(0, new CameoTime(), new CameoCue());
	}
	
	
	private class TrackingChannel {
		int _channel;
		short _oldLevel;
		short _newLevel;
		
		TrackingChannel(int c, short o, short n) {
			_channel = c;
			_oldLevel = o;
			_newLevel = n;
		}
		
		public String toString(){
			return _channel+" "+_oldLevel+" -> "+_newLevel;
		}
	}

	public CueListData distill() {
		CueListData cld = new CueListData();
		
		for(ConsoleFade cue : _cues){
			cld.add(cue.distill());
		}
		
		return cld;
	}

	public void extractFrom(CueListData data) {
		//_totalChannels = data.getTotalChannels();
		_cues = new LinkedList<ConsoleFade>();
		
		for(FadeData cd : data){
			ConsoleFade cue = new CameoFade();
			cue.extractFrom(cd);
			addCueOnly(cue);
		}
		
		//TODO hook up follow cues
	}
	
	public Iterator<ConsoleFade> iterator() {return _cues.iterator();}
}
