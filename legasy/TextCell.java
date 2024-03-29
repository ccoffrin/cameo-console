/*
 * @(#) TextCell.java	1.0 11/2/03
 *
 * Copyright (C) 2001 Gaudenz Alder
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 */
package legasy;

public class TextCell extends org.jgraph.graph.DefaultGraphCell {

	private boolean multiLined = true;

    public TextCell() {
      this(null);
    }

    public TextCell(Object userObject) {
      this(userObject, true);
    }

    public TextCell(Object userObject, boolean multiLined) {
      super(userObject);
      this.multiLined = multiLined;
    }


	public boolean isMultiLined() {
		return multiLined;
	}


}
