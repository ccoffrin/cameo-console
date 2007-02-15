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

public class CameoChannel implements ConsoleChannel{
	protected short _level;

	public CameoChannel() {_level = OUT;}
	public CameoChannel(short l) {_level = cleanShort(l);}
	 
	public CameoChannel duplicate() {return new CameoChannel(_level);}
	
	public short getLevel() {return _level;}

	public void setLevel(short l){_level = cleanShort(l);}
	
	public boolean isOut(){return _level == OUT;}
	
	private short cleanShort(short l){
		if(l <= OUT) 
			return OUT;
		return (short)(l % 256);
	}
	
	public String toString() {return Short.toString(_level);}
	
	public String getLevelString(){
		if(_level >= 255)
			return "FL";
		else //casting to int takes care of digit arounding maybe?
			return Integer.toString((int)(_level/2.56+0.5));
	}
	
}
