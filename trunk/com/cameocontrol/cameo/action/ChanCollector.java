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

import com.cameocontrol.cameo.control.ChannelSet;

public class ChanCollector extends BVisitor{
	private ChannelSet _channels;
	
	public ChanCollector() {
		_channels = new ChannelSet();
	}
	
	public ChannelSet collect(ACTAction a) {
		_channels = new ChannelSet();
		a.visit(this);
		return _channels;
	}
	
	private int findMin(ACTChannel c1, ACTChannel c2) {
		if(c1._channel < c2._channel)
			return c1._channel;
		return c2._channel;
	}
	
	private int findMax(ACTChannel c1, ACTChannel c2) {
		if(c1._channel > c2._channel)
			return c1._channel;
		return c2._channel;
	}
	
	public void applyACTChannel(ACTChannel n) {
		_channels.add(n._channel-1);
	}

	public void applyACTChanThru(ACTChanThru n) {
		for(int x = findMin(n._left, n._right)-1; x <= findMax(n._left, n._right)-1; x++)
			_channels.add(x);
	}
	
	public void applyACTChanExcept(ACTChanExcept n) {
		ChanCollector lr = new ChanCollector();
		ChannelSet l = lr.collect(n._left);
		ChannelSet r = lr.collect(n._right);
		_channels.addAll(l.remove(r));
	}
	
	public void applyACTChanAnd(ACTChanAnd n) {
		ChanCollector lr = new ChanCollector();
		_channels.addAll(lr.collect(n._left));
		_channels.addAll(lr.collect(n._right));
	}
}
