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

package com.cameocontrol.cameo;

import com.cameocontrol.cameo.action.ActionInterpreter;
import com.cameocontrol.cameo.control.CameoConsole;
import com.cameocontrol.cameo.output.CameoOutputManager;
import com.cameocontrol.cameo.resource.CameoResourceManager;

public class Cameo {
	
	public static void main(String[] args){
		new Cameo().run();
	}
	
	public void run()
	{
		CameoResourceManager crm = new CameoResourceManager();
		CameoOutputManager oc = new CameoOutputManager(crm.getPrefrences(), crm.getPatch());
		CameoConsole cc = new CameoConsole(crm.getPrefrences(), oc, crm, crm.getShow());
		ActionInterpreter ai = new ActionInterpreter(cc, oc, crm, cc);
	}

}
