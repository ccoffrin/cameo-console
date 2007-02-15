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

package com.cameocontrol.cameo.resource;

import com.cameocontrol.cameo.file.Loadable;
import com.cameocontrol.cameo.file.Savable;
import com.cameocontrol.cameo.file.adt.Data;

/**
 * And abstraction of what a resource is.  composes other interfaces to allow, loading, saving, and updateing 
 * 
 * @author Carleton
 *
 */
public abstract interface Resource<T extends Data> extends Savable<T>, Loadable<T> {

}
