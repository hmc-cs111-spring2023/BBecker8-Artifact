# Java app with JavaFX + Spreadsheet.com API

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

Its That easy!

### Here are some tips for proper usage of the the interface with the DSL:

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

