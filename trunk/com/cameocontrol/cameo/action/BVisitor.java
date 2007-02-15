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

public class BVisitor implements IVisitor{

	public void doIt(ACTAction n) {}

	public void applyACTChanAtLevel(ACTAtLevel n) {}
	public void applyACTBack(ACTBack n) {}
	public void applyACTDeleteCue(ACTDeleteCue n) {}
	public void applyACTGo(ACTGo n) {}
	public void applyACTGotoCue(ACTGotoCue n) {}
	public void applyACTLoadCue(ACTLoadCue n) {}
	public void applyACTRecordCue(ACTRecordCue n) {}
	public void applyACTChanAtCue(ACTAtCue n) {}
	public void applyACTDimAtLevel(ACTDimAtLevel n) {}
	public void applyACTOut(ACTOut n) {}
	public void applyACTPlus(ACTPlus n) {}
	public void applyACTMinus(ACTMinus n) {}
	public void applyACTCue(ACTCue n) {}
	public void applyACTCueMod(ACTCueMod n) {}
	public void applyACTLevelMod(ACTLevelMod n) {}

	public void applyACTChannelSelection(ACTChanSelection n) {}
	
	public void applyACTChannel(ACTChannel n) {}
	
	public void applyACTChanThru(ACTChanThru n) {
		n._left.visit(this);
		n._right.visit(this);
	}
	
	public void applyACTChannelBinOp(ACTChanBinOp n) {
		n._left.visit(this);
		n._right.visit(this);
	}
	public void applyACTChanExcept(ACTChanExcept n) {applyACTChannelBinOp(n);}
	public void applyACTChanAnd(ACTChanAnd n) {applyACTChannelBinOp(n);}

	public void applyACTChanSelect(ACTChanSelect n) {
		n._channels.visit(this);
	}

	public void applyACTPatchChan(ACTPatchChan n) {}
	public void applyACTUnpatchDim(ACTUnpatchDim n) {}
	public void applyACTUnpatchChan(ACTUnpatchChan n) {}
	public void applyACTPatch1to1(ACTPatch1to1 n) {}

	public void applyACTCueMove(ACTCueMove n) {}

	public void applyACTNoOp(ACTNoOp n) {}

	public void applyACTNext(ACTNext n) {}

	public void applyACTPrevious(ACTPrevious n) {}

	public void applyACTSettingsShowGenralMod(ACTSettingsShowGenralMod n) {}

	public void applyACTSettingsShowChannelMod(ACTSettingsShowChannelMod n) {}

	public void applyACTSettingsPrefMod(ACTSettingsPrefMod n) {}

	public void applyACTPatchClear(ACTPatchClear n) {}
	public void applyACTCuesClear(ACTCuesClear n) {}

	public void applyACTSaveShow(ACTSaveShow n) {}

	public void applyACTLoadShow(ACTLoadShow n) {}

	public void applyACTChanLocation(ACTChanLocation n) {}
}
