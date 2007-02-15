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

import java.util.HashSet;
import java.util.Iterator;

public class ChannelSet implements Iterable<Integer>{
	private HashSet<Integer> _channels;
	
	public ChannelSet() {
		_channels = new HashSet<Integer>();
	}
	
	public ChannelSet(int c) {
		_channels = new HashSet<Integer>();
		_channels.add(c);
	}
	
	public void add(int c){
		_channels.add(new Integer(c));
	}
	
	public void addAll(ChannelSet c){
		for(Integer i : c)
			_channels.add(new Integer(i.intValue()));
	}
	
	public boolean contains(int x) {
		return _channels.contains(new Integer(x));
	}

	public ChannelSet remove(ChannelSet sub) {
		ChannelSet temp = new ChannelSet();
		for(Integer i : _channels)
			if(!sub.contains(i))
				temp.add(new Integer(i));
		return temp;
	}
	
	public int min() {
		int min = _channels.iterator().next().intValue();
		for(Integer i : _channels)
			if(i.intValue() < min)
				min = i.intValue();
		return min;
	}
	
	public int max() {
		int max = _channels.iterator().next().intValue();
		for(Integer i : _channels)
			if(i.intValue() > max)
				max = i.intValue();
		return max;
	}
	
	public Iterator<Integer> iterator() {
		return _channels.iterator();
	}
	
	public void clear() {_channels.clear();}
	
	public int size() {return _channels.size();}
	
	public boolean isSingle() {return _channels.size() == 1;}
	public int getSingle() {return _channels.iterator().next().intValue();}
	
}
