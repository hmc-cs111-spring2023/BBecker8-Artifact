# Domain Specific Language for Football Stats
## About me / instriation
DSL for live sports statistics in Java. The gist of this project was to build a new language for parents and stats keepers that allows for quick input and shows proof of concept for live stat keeping. This project is focused on football stat keeping. Other applications of this language would be for live stat sharing across multiple platforms and API's. I came up with this idea when observing my dad keeping stats for my brother's high school football games. My dad volunteered to keep stats each game for my brother's football program, he would then send the stats to the coaches to input into maxpreps. I thought it might be nice for my dad to have such a language that builds these stat sheets for him as well as could potentially be used later to connect to other platforms API's such as maxpreps and fill in player stats during game time. This would fix the issue of using pen and paper to write down stats then transferring them to some other source. Also for someone like me who wanted to keep tabs on how my brother's team was playing but had no way to watch, such a language could be used to display a live play by play. 
## Java app with JavaFX + Spreadsheet.com API

This app should compile and run in codespaces.

## JavaFX

1. Start up codespaces.
2. Eventually once it starts up, codespaces should open another tab with a remote desktop (you may have to enable popups). _(If it doesn't open in a new window, you can open one yourself by clicking on the PORTS tab and using the "Open in Browswer" button, under "Local Address".)
3. Click the `Connect` button, and log into the remote desktop with password `abc123`.
4. Back in code spaces, run the following commands in the terminal:
```
cd my-dsl
gradle run
```
5. The JavaFX window should pop up in the remote desktop. Close the window to quit the application.

## How it works
The key parts are:
- The [`.devcontainer`](./.devcontainer) folder, which sets up the container than can display GUIs, plus the remote desktop.
- The Gradle project format, created by running the `gradle init` command in the `my-dsl` folder, choosing a Desktop Java app and the remaining default options.
- The [`my-dsl/app/build.gradle`](./my-dsl/app/build.gradle) file, which configures the dependencies for JavaFX and other dependencies.

## How to Football Stats DSL

1. Begin by following the instuctions above to open the app interface.
2. Once open type the DSL language into the text box and hit enter.
3. Go to ([here](https://app.spreadsheet.com/workbooks/7lWIK16tQ2W-ZplUvGrymAdkUM7qnIR9SCWrelxIDGlg?mode=sharing)) and check out the live stats that you just entered. 
4. Use the 'Start New Game: Title' command to build another spreadsheet.
5. Use the 'Edit Past Game: spreadheet#' to edit an existing spreadsheet.

Its That easy!

### Here are some tips for proper usage of the the interface with the DSL:
Use the 'Start New Game: Title' command to build another spreadsheet. Title should be replaced with the title of your spreadsheet.

Use the 'Edit Past Game: spreadheet#' to edit an existing spreadsheet. spreadheet# should be replaced with a number representing the nth spreadsheet in the workspace. For instance say I have 3 sheets the left most would be spreadheet# 1 and the right most would be spreadheet# 3.

Input Format must be in the following form:

(PlayType) (player#1) (numberYards) (player#2) (Outcome)

Where each group is seperated only by a space. Also not every group Is required.

### Player Number #1 - A number representing a player's number (required)

### Number of Yards - The number of yards on the given play given in the format of a number followed by a 'y' (not required)
Example: 22y, 11y, -3y, -50y

### Player Number #2 - A number representing a player's number (only used on a pass play)

### Play Type - A word or letter representing a possible play (required)
Possible types for plays are:

#### Offensive Play Types:

Pass - can be written as the following: Pass P p

Run - can be written as the following: Run Rush R r

Reception - can be written as the following: defined by a pass play

Kickoff - can be written as the following: Kick K k

Punt - can be written as the following: Punt punt PT pt

Field Goal - can be written as the following: FG fg

PAT - can be written as the following: Pat PAT pat

Kick off return (KOR)- can be written as the following: KOR kor

Punt Return (POR)- can be written as the following: POR por PR pr

Sack - can be written as the following: Sack sck


#### Defensive Play Types:

Tackle for loss - can be written as the following: forloss fl

Sack - can be written as the following: sack sck

Interception - can be written as the following: int

Tipped Pass - can be written as the following: tippass tp tipp

Forced Fumble - can be written as the following: fum ff ffum forcedf

Blocked Kick - can be written as the following: blockedk bk bkick

Safety - can be written as the following: safety sfty

### Outcome - An additional tag representing an outcome of a play

#### Possible Outcomes for plays are:
TD - represents a Touch Down

INC - represents an incomplete Pass

INT - represents an Interception thrown

FC - represents a fair catch

FG - represents a field Goal

PAT - represents a Point after attempt

PUNT - represents a Punt

REC - represents a recovered fumble

NREC - represents a non-recovered fumble

TB - represents a Touch Back

GOOD - represents a good kick

MISS - represents a missed kick

