package com.cameocontrol.cameo.util;

public class ShortUntils {

	public static short sumShort(short s1, short s2) {
		int s = s1+s2;
		if(s > 255)
			return (short)255;
		else
			return (short)s;
	}
	
	public static short subShort(short s1, short s2) {
		int s = s1-s2;
		if(s < 0 && s1 == 0)
			return (short)-1;
		if(s < 0)
			return (short)0;
		else
			return (short)s;
	}
}
