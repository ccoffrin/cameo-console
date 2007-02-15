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

package com.cameocontrol.cameo.dataStructure;

import java.util.Random;

public class LevelFactory {
	private Random _rand;
	public LevelFactory() {_rand = new Random();}
	public short level(int i) {
		if(i < 0)
			return 0;
		if(i > 255)
			return 255;
		return (short)i;
	}
	
	public short rand() {
		//System.out.print("asdas*");
		return (short)(Math.abs((_rand.nextInt())%255));
	}
}
