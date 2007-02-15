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

public interface IVisitor {
	
	public abstract void doIt(ACTAction n);
	public abstract void applyACTChanAtLevel(ACTAtLevel n);
	public abstract void applyACTBack(ACTBack n);
	public abstract void applyACTDeleteCue(ACTDeleteCue n);
	public abstract void applyACTGo(ACTGo n);
	public abstract void applyACTGotoCue(ACTGotoCue n);
	public abstract void applyACTLoadCue(ACTLoadCue n);
	public abstract void applyACTRecordCue(ACTRecordCue n);
	public abstract void applyACTChanAtCue(ACTAtCue n);
	public abstract void applyACTDimAtLevel(ACTDimAtLevel n);
	public abstract void applyACTOut(ACTOut n);
	public abstract void applyACTChannelSelection(ACTChanSelection n);
	public abstract void applyACTPlus(ACTPlus n);
	public abstract void applyACTMinus(ACTMinus n);
	public abstract void applyACTCue(ACTCue n);
	public abstract void applyACTCueMod(ACTCueMod n);
	public abstract void applyACTLevelMod(ACTLevelMod n);
	public abstract void applyACTChannel(ACTChannel n);
	public abstract void applyACTChannelBinOp(ACTChanBinOp n);
	public abstract void applyACTChanThru(ACTChanThru n);
	public abstract void applyACTChanExcept(ACTChanExcept n);
	public abstract void applyACTChanAnd(ACTChanAnd n);
	public abstract void applyACTChanSelect(ACTChanSelect n);
	public abstract void applyACTPatchChan(ACTPatchChan n);
	public abstract void applyACTUnpatchDim(ACTUnpatchDim n);
	public abstract void applyACTUnpatchChan(ACTUnpatchChan n);
	public abstract void applyACTPatch1to1(ACTPatch1to1 n);
	public abstract void applyACTCueMove(ACTCueMove n);
	public abstract void applyACTNoOp(ACTNoOp n);
	public abstract void applyACTNext(ACTNext n);
	public abstract void applyACTPrevious(ACTPrevious n);
	public abstract void applyACTSettingsShowGenralMod(ACTSettingsShowGenralMod n);
	public abstract void applyACTSettingsShowChannelMod(ACTSettingsShowChannelMod n);
	public abstract void applyACTSettingsPrefMod(ACTSettingsPrefMod n);
	public abstract void applyACTPatchClear(ACTPatchClear n);
	public abstract void applyACTCuesClear(ACTCuesClear n);
	public abstract void applyACTSaveShow(ACTSaveShow n);
	public abstract void applyACTLoadShow(ACTLoadShow n);
	public abstract void applyACTChanLocation(ACTChanLocation n);
}
