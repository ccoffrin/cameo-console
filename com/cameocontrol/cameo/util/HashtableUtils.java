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

package com.cameocontrol.cameo.util;

import java.util.Hashtable;

public class HashtableUtils {
	public static int checkInt(int value, String tag, Hashtable<String,String> data){
		if(data.containsKey(tag))
			return Integer.parseInt(data.get(tag));
		return value;
	}
	
	public static String check(String value, String tag, Hashtable<String,String> data){
		if(data.containsKey(tag))
			return data.get(tag);
		return value;
	}
}
