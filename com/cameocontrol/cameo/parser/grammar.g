header {
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
	
package com.cameocontrol.cameo.parser;
import com.cameocontrol.cameo.action.*;

}

class cmdParser extends Parser;

options {
   k=5;
   defaultErrorHandler = false;
}

{
	
	
	protected int getInt(Token t) {
		if(t == null)
			return -1;
		else
			return Integer.parseInt(t.getText());
	}
	
	protected int getTime(Token t) {
		if(t == null)
			return -1;
		else
			return Integer.parseInt(t.getText())*1000;
	}
	
	protected int getFloat(Token t) {
		if(t == null)
			return -1;
		else
			return (int)(Float.parseFloat(t.getText())*1000);
	}
	
	protected String getText(Token t) {
		if(t == null)
			return null;
		else
			return t.getText();
	}
	
	public void reportError(RecognitionException ex) {
		super.reportError(ex);
	}
	public void reportError(String msg) {
		super.reportError(msg);
	}
}

command returns [ACTAction a] {ACTChanSelection cs; ACTCue cue; int num; boolean conf=false;}
	: {a = new ACTNoOp();}
	| GO {a = new ACTGo();}
	| BACK {a = new ACTBack();}
	| NEXT {a = new ACTNext();}
	| PREV {a = new ACTPrevious();}
	| PLUS l8:INT {a = new ACTPlus(getInt(l8));}
	| MINUS l9:INT {a = new ACTMinus(getInt(l9));}
	| (AT)+ (
	  		  (OUT)? {a = new ACTOut();} 
		 	| FULL {a = new ACTAtLevel(100);}
		 	| PLUS l4:INT {a = new ACTPlus(getInt(l4));}
		 	| MINUS l5:INT {a = new ACTMinus(getInt(l5));}
			| l6:INT {a = new ACTAtLevel(getInt(l6));} 
			| CUE num=mixNum {a = new ACTAtCue(num);}
		 )
	| cs=chans ((AT)+
			(
			  (OUT)? {a = new ACTOut(cs);} 
		 	| FULL {a = new ACTAtLevel(cs,100);}
		 	| PLUS l1:INT {a = new ACTPlus(cs, getInt(l1));}
		 	| MINUS l2:INT {a = new ACTMinus(cs, getInt(l2));}
		 	| l3:INT {a = new ACTAtLevel(cs,getInt(l3));}
		 	| CUE num=mixNum {a = new ACTAtCue(cs,num);}
			) | {a = new ACTChanSelect(cs);})
	| DIM c6:INT (AT)+ a=dimLevelRef[c6.getText()] 
	| RECORD cue=cueDef ( {a = new ACTRecordCue(cue, false);} | CONFERM {a = new ACTRecordCue(cue, true);} )
	| GOTO cue=cueSig {a = new ACTGotoCue(cue);}
	| LOAD cue=cueSig {a = new ACTLoadCue(cue);} 
	| DEL cue=cueSig ( {a = new ACTDeleteCue(cue, false);} | CONFERM {a = new ACTDeleteCue(cue, true);} )
	| a=cueMod
;

chans returns [ACTChanSelection cs] {ACTChanSelection l; ACTChanSelection r;}
	: (CHAN)? cs=tb (  PLUS r=tb {cs = new ACTChanAnd(cs, r);}
					| MINUS r=tb {cs = new ACTChanExcept(cs,r);}
					)*
;

tb returns [ACTChanSelection cs] {ACTChannel c;}
	: cs=base (THRU c=base {cs = new ACTChanThru((ACTChannel)cs, c);})*
;

base returns [ACTChannel cs]
	: c1:INT {cs = new ACTChannel(getInt(c1));}
;

chanLevelRef[ACTChanSelection cs] returns [ACTAction a] {int num;}
	: l:INT {a = new ACTAtLevel(cs,getInt(l));}
	| CUE num=mixNum {a = new ACTAtCue(cs,num);}
;

mixNum returns [int i]
	: (cn:INT {i = getTime(cn);} | fl:FLOAT {i = getFloat(fl);})
;

cueSig returns [ACTCue c] {int cnum = -1; int t1; int t2;}
	: (CUE)? (cnum=mixNum)? {c = new ACTCue(cnum);}
	  (TIME (t1=mixNum {c.setTime(t1);} | t1=mixNum SPLIT t2=mixNum {c.setUpTime(t1); c.setDownTime(t2);})
	  (DELAY (t1=mixNum {c.setDelayTime(t1);}| t1=mixNum SPLIT t2=mixNum {c.setDelayUpTime(t1); c.setDelayDownTime(t2);}))?)?
;

cueDef returns [ACTCue c] {int num;}
	: c = cueSig
	  (LINK num=mixNum {c.setNextCue(num);})?
	  (FOLLOW num=mixNum {c.setFollowTime(num);})?
;

cueMod returns [ACTCueMod c = new ACTCueMod()] {int num; int t1; int t2;}
	: (CUE num=mixNum {c.setCueNumber(num);})? (
	  	(TIME (t1=mixNum {c.setTime(t1);} | t1=mixNum SPLIT t2=mixNum {c.setUpTime(t1); c.setDownTime(t2);}))
	  	| (DELAY (t1=mixNum {c.setDelayTime(t1);}| t1=mixNum SPLIT t2=mixNum {c.setDelayUpTime(t1); c.setDelayDownTime(t2);}))
	  	| (LINK num=mixNum {c.setNextCue(num);})
	  	| (FOLLOW ft:INT {c.setFollowTime(getTime(ft));})
	  )+
;
	 
dimLevelRef[String channel] returns [ACTAction a] {int cnum;}
	: FULL {a = new ACTDimAtLevel(Integer.parseInt(channel),100);}
	| l:INT {a = new ACTDimAtLevel(Integer.parseInt(channel),getInt(l));}
;

class cmdLexer extends Lexer;

options {
   k=4;
   testLiterals=false;
}


//PERIOD: '.';

OUT:		"out";

RECORD:	"record";
GO:		"go";
BACK:	"back";
GOTO:	"goto";
LOAD:	"load";
CUE: 	"cue";
CHAN:	"channel";
DIM:		"dimmer";
DEL:		"delete";
DELAY:	"delay";
LINK:	"link";
FOLLOW:	"follow";
TIME:	"time";
GROUP:	"group";
LABEL:	"label";
TRACK:	"track";
//CUEONLY:	"cue only";
NEXT:	"next";
PREV:	"previous";
CONFERM:"confirm";

THRU:	"->";
SPLIT:	'/';
PLUS	:	'+';
MINUS:	'-';

FULL:	("full"|"FL");
AT: 		('@'|"at");

//LEVEL: ('0' | "100" | ('0'..'9') | ('0'..'9')('0'..'9'))
//protected
//INT: ('0' | ('1'..'9')('0'..'9')*);
protected
INT: ('0'..'9')+;

protected
FLOAT: INT '.' INT;

INT_FLOAT
	: (INT '.') => FLOAT {$setType(FLOAT);}
	| INT				{$setType(INT);}
;
//FLOAT: INT '.' INT;

//EOF: '\uFFFF';
// whitespace
WS: 
    ( ' ' 
    | '\t' 
    | ("\r\n"|'\r'|'\n') 
    )
    { $setType(Token.SKIP);}
   ;

// \uFFFF is to account for EOF. A comment on the last line of the file (without a \r\n) _does_ happen.

//CMT: "//" (~('\n'|'\r'|'\uFFFF'))* ("\r\n" |'\r'| '\n' | '\uFFFF') {$setType(Token.SKIP);};

//protected
//INCOMPLETE_ML_COMMENT: "/*";
                            
//ML_COMMENT: INCOMPLETE_ML_COMMENT (  { LA(2) != '/' }? '*'| ~('*'))* "*/";


