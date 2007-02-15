package com.cameocontrol.cameo.output;

import com.cameocontrol.cameo.control.CameoChannel;

public class LiveChannel extends CameoChannel{
	public enum ChannelState {UNMARKED, INCREASING, DECREASING, CHANGED}

	private ChannelState _state;

	public LiveChannel(){
		_state = ChannelState.UNMARKED;
	}
	
	public void setState(ChannelState s) {_state = s;}
	public ChannelState getState() {return _state;}
	
	public void setLevel(short l){
		short oldLevel = _level;
		super.setLevel(l);
		
		if(_state != ChannelState.CHANGED){
			if(oldLevel > _level)
				_state = ChannelState.DECREASING;
			if(oldLevel < _level)
				_state = ChannelState.INCREASING;
		}
	}
	
	public void updateLevel(short l){
		super.setLevel(l);
		_state = ChannelState.CHANGED;
	}
	
	
}
