package dmx;
/************************************************************************************
 *  Copyright 2006 Carleton Coffrin
 * 
 *  This file is part of Straight DMX.
 *  
 *  Straight DMX is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *  
 *  Straight DMX is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with Straight DMX; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *  
 *************************************************************************************/

public class DMXStub implements DMX{

	public short[] _out;
	
	public DMXStub(short s){
		_out = new short[512];
		
		for(int x=0; x < 512; x++)
			_out[x] = 0;
	}
	public boolean setStartCode(short s) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean open(int b, int r) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean close() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isOpen() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean profile(int i, short[] profile) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean profile(short[][] profile) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean clearProfile(int i) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean clearProfile() {
		// TODO Auto-generated method stub
		return true;
	}

	public void stop() {
		// TODO Auto-generated method stub
		
	}

	public void setSingle(int i, short Data) {
		// TODO Auto-generated method stub
		
	}

	public void setSingle(int i, short Data, int t) {
		// TODO Auto-generated method stub
		
	}

	public void quickLoad(short[] Data) {
		// TODO Auto-generated method stub
		
	}

	public void fullLoad(short[] Data, int ut, int dt, int d) {
		// TODO Auto-generated method stub
		
	}

	public void partialLoad(short[] Data, int[] change, int ut, int dt, int udt, int ddt) {
		// TODO Auto-generated method stub
		
	}

	public void complexLoad(short[] Data, int[] change, int[] ut, int[] dt, int udt, int ddt) {
		// TODO Auto-generated method stub
		
	}

	public short[] getCurrentFade() {
		return _out;
	}

	public short[] getCurrentOutput() {
		return _out;
	}

	public String getVersion() {
		// TODO Auto-generated method stub
		return null;
	}
	public void partialLoad(short[] Data, int ut, int dt, int udt, int ddt) {
		// TODO Auto-generated method stub
	}
	public boolean isSimulation() {
		return true;
	}
	public int stopFade() {
		// TODO Auto-generated method stub
		return 0;
	}
	public boolean clearProportionalOutput(int index) {
		// TODO Auto-generated method stub
		return true;
	}
	public boolean clearProportionalOutputs() {
		// TODO Auto-generated method stub
		return true;
	}
	public boolean setGrandMaster(short level) {
		// TODO Auto-generated method stub
		return true;
	}
	public boolean setProportionalOutput(int index, short level) {
		// TODO Auto-generated method stub
		return true;
	}
	public boolean setProportionalOutputs(short[] levels) {
		// TODO Auto-generated method stub
		return true;
	}

}
