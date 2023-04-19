package my.dsl.Player;
import java.util.ArrayList;


public class PATFGPlayer extends Player{
	
	public PATFGPlayer(int number) {
		super(number);
		super.eventCount = 0;
		super.madePATs = 0;
		super.numPATs = 0;
		super.percentPAT = 0.0;
		super.madeFGs = 0;
		super.numFGs = 0;
		super.percentFG = 0.0;
		super.madeTOT = 0;
		super.points = 0;
	}

	// setters for the PAT or FG Player for each stat type 
	
	public PATFGPlayer updatePlayer() {
		super.eventCount += 1;
		if ( (((double) super.madePATs)/((double)super.numPATs)) > 0.0) {
			super.percentPAT = ((double) super.madePATs)/((double)super.numPATs);
		} else {
			super.percentPAT = 0.0;
		}
		
		if ( (((double) super.madeFGs)/((double)super.numFGs)) > 0.0) {
			super.percentFG = ((double) super.madeFGs)/((double)super.numFGs);
		} else {
			super.percentFG = 0.0;
		}
		
		super.madeTOT = super.madePATs + super.madeFGs;
		super.points = super.madePATs + (3 * super.madeFGs);
		return this;
	}
	
	public PATFGPlayer setLogest(int yards) {
		if (yards > super.longest) {
			super.longest = yards;
		}
		return this;
	}
	
	public PATFGPlayer setNumPAT(int n) {
		super.numPATs += n;
		return this;
	}
	
	public PATFGPlayer setNumFG(int n) {
		super.numFGs += n;
		return this;
	}
	
	public PATFGPlayer setMadePAT(String isMade) throws Exception {
		if (isMade.equals("GOOD")) {
			super.madePATs += 1;
		} else if (isMade.equals("MISS") || isMade.equals("null")) {
			return this;
		} else {
			throw new Exception("Outcome did not match", new Throwable(isMade));
		}
		return this;
		
	}
	
	public PATFGPlayer setMadePAT(int isMade) {
		super.madePATs += isMade;
		return this;
	}
	
	public PATFGPlayer setMadeFG(String isMade) throws Exception {
		if (isMade.equals("GOOD")) {
			super.madeFGs += 1;
		} else if (isMade.equals("MISS")||isMade.equals("null")) {
			return this;
		} else {
			throw new Exception("Outcome did not match", new Throwable(isMade));
		}
		return this;
	}
	
	public PATFGPlayer setMadeFG(int isMade) {
		super.madeFGs += isMade;
		return this;
	}
	
	@Override
	public String toString() {
		return "PATFGPlayer [number=" + number + ", eventCount=" + eventCount + ", longest="
				+ longest + ", madePATs=" + madePATs + ", numPATs=" + numPATs + ", madeFGs=" + madeFGs + ", numFGs="
				+ numFGs + ", percentPAT=" + percentPAT + ", percentFG=" + percentFG + ", madeTOT=" + madeTOT
				+ ", points=" + points + "]";
	}
	
	public ArrayList<Object> toList() {
		ArrayList<Object> newList = new ArrayList<Object>();
		newList.add(number);
		newList.add(eventCount);
		newList.add(madePATs);
		newList.add(numPATs);
		newList.add(percentPAT);
		newList.add(madeFGs);
		newList.add(numFGs);
		newList.add(percentFG);
		newList.add(longest);
		newList.add(madeTOT);
		newList.add(points);
		
		return newList;
		
	}
}

