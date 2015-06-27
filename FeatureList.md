#A prioritized list of current and future features

## Currently Implemented ##
  * Live Mode
  * Patch
  * Tracking & Cue Only Record Modes
  * Channel View
  * Magic Sheet View
  * Cue List View
  * Spread Sheet View
  * Windows Platform

## Minimum Functionality for Alpha Release ##
  * Faster Channel Rendering
  * Proportional Patch
  * Timed Auto Backup
  * Streamlined Packaging

## Minimum Functionality for Public Beta Release ##
  * Blind Mode
  * Show Running Mode
  * Colored Channel Levels
  * Groups
  * Parts
  * Channel Profile Editor
  * OS X Platform
  * User Guide

## Important ##
  * Wi-Fi RFU (possibly use the ZipIt)
  * Linux Platform
  * Cameo Live CD
  * Console Only mode
  * Save / Export to open formats (ASCII / .csv)
  * Park

## Convenient ##
  * Cue Profiles
  * Cue labeling - possible to assign 1 label per part
  * Everything Labeling - Cues, parts, effects, subs, macros, groups, profiles, channels, dimmers, etc....

## Dreams ##
  * ability to load only a single or range of cues, groups, subs, patch, macros, effects from a saved show (and have it track (or not)).

  * pass variables to macros, ie: current cue, latest channel selected, default fade times. so you could write a macro like:
```
GROUP 1 RECORD CUE <thisCue> LAST PART 7 TIME 6 WAIT <time of cue before $thiscue> ENTER
```

  * do math on the command line. examples:
```
CUE 1 > 7 TIME +4 ENTER
GROUP 4 @ +20 ENTER
CHAN 1 > 100 STEP 2 @ 50 ENTER     (every second channel between 1 and 100)
GROUP 6 ON @ +20 ENTER        (every channel that's on in group 6 up by 20, swiped from Strand)
CHAN 5 @ /70 ENTER   (puts chan 5 @ 70% of current level)
```
  * other whacky stuff on the command line
```
RECORD CUE 6 > 10 ENTER
GROUP 1 @ GROUP 2 ENTER
GROUP 2 > GROUP 4 @ FULL ENTER  (calls group 2 and group 3 and group 4)
GROUP 1 @ CUE 5 ENTER
GO TO CUE NEXT ENTER  
CHAN 4 @ 50 TIME 7 ENTER  (who needs sneak?)
GROUP 1 > 6 RECORD SUB 1 > 6 ENTER
CUE 1 > 4 RECORD GROUP 1 > 4 ENTER
```
  * fanning
```
CHAN 1 > 10 @ FAN 40 > FULL ENTER
CUE 6 PART 1 > CUE 6 PART 12 TIME FAN 2 > 24 ENTER
```
  * effects - able to load effects into subs, or run them in cues

  * affect subs from the command line (faders could be "motorized")
```
SUB 1 @ FL ENTER
SUB 2 @ 50 TIME 5 ENTER
SUB 1 > 10 @ 0 ENTER
```
