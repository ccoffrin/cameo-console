package com.cameocontrol.cameo.action;

public class ACTChanLocation extends ACTAction{
	int _channel;
	double _x;
	double _y;
	
	public ACTChanLocation(int c, double x, double y){
		_channel = c;
		_x = x;
		_y = y;
	}

	@Override
	public void visit(IVisitor v) {
		v.applyACTChanLocation(this);
	}
}
