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

import java.util.Iterator;
import java.util.Hashtable;

import com.cameocontrol.cameo.file.adt.GroupData;
import com.cameocontrol.cameo.file.adt.GroupListData;

/// List of cue tranistions with a pointer to the current cue

//What are the implications if this class actully extends LinkedList?
//maybe not becouse of the speshalized nature of the add and remove?

		
public abstract class BasicGroupList implements ConsoleGroupList {
	protected Hashtable<Integer, ConsoleGroup> _groups;

	public BasicGroupList(){
		_groups = new Hashtable<Integer, ConsoleGroup>();
	}

	public void clearGroups() {
		_groups = new Hashtable<Integer, ConsoleGroup>();
	}

	public ConsoleGroup deleteGroup(int groupNumber) {
		if(_groups.contains(groupNumber))
			return _groups.remove(groupNumber);
		return null;
	}

	public ConsoleGroup getGroup(int groupNumber) {
		return _groups.get(groupNumber);
	}

	public void recordGroup(ConsoleGroup group) {
		_groups.put(group.getNumber(), group);
	}

	public GroupListData distill() {
		GroupListData gld = new GroupListData();
		
		for(ConsoleGroup group : _groups.values()){
			gld.add(group.distill());
		}
		
		return gld;
	}

	public void extractFrom(GroupListData data) {
		_groups = new Hashtable<Integer, ConsoleGroup>();
		
		for(GroupData gd : data){
			ConsoleGroup group = new CameoGroup();
			group.extractFrom(gd);
			recordGroup(group);
		}
	}	

	public Iterator<ConsoleGroup> iterator() {
		return _groups.values().iterator();
	}

}
