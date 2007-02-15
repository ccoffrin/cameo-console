package com.cameocontrol.cameo.output;

public interface OutputManager {

	//Dimmers
	public void dimAt(int d, short l);
	
	//Patch
	public void patchChan(int c, int d);
	public void unpatchChan(int c);
	public void unpatchDim(int d);
	public void patch1to1();
	public void patchClear();
	
	//Setting Manipulation
	public void refreshRate(int r);
	public void startCode(short s);
	
}
