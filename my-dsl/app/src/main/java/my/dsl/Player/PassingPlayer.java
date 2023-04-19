package my.dsl.Player;
import java.util.ArrayList;

/*
 * class representing a passing player and their possible stats
 */
//@JsonIgnoreType
public class PassingPlayer extends Player{

	public PassingPlayer(int number) {
		super(number);
		super.eventCount = 0;
		super.yards = 0;
		super.avg = 0.0;
		super.tds = 0;
		super.longest = 0;
		super.comp = 0;
		super.compPercent = 0.0;
		super.interceptionsQB = 0;
		super.inc = 0;
	}

	// setters for the passing Player for each stat type 
	public PassingPlayer updatePlayer() {
		super.eventCount += 1;
		super.comp = super.eventCount - super.interceptionsQB - super.inc;
		super.avg = ((double) super.yards)/((double)super.comp);
		super.compPercent = (((double)super.comp)/((double)super.eventCount));
		return this;
	}
	
	public PassingPlayer setYards(int yards) {
		super.yards += yards;
		if (yards > super.longest) {
			super.longest = yards;
		}
		return this;
	}
	
	public PassingPlayer setTDs(String tds) throws Exception {
		if (tds.equals("TD")) {
			super.tds += 1;
		} else if (tds.equals("null")|| tds.equals("INC") || tds.equals("INT")){
			return this;
		} else {
			throw new Exception("Outcome did not match", new Throwable(tds));
		}
		return this;
	}
	
	
	public PassingPlayer setINTs(String ints) throws Exception {
		if (ints.equals("INT")) {
			super.interceptionsQB += 1;
		} else if (ints.equals("null") || ints.equals("TD") || ints.equals("INC")){
			return this;
		} else {
			throw new Exception("Outcome did not match", new Throwable(ints));
		}
		return this;
	}
	
	public PassingPlayer setINCs(String incs) throws Exception {
		if (incs.equals("INC")) {
			super.inc += 1;
		} else if (incs.equals("null")|| incs.equals("TD") || incs.equals("INT")){
			return this;
		} else {
			throw new Exception("Outcome did not match", new Throwable(incs));
		}
		return this;
	}

	public PassingPlayer setTDs(int tds) {
		super.tds += tds;
		return this;
	}
	
	
	public PassingPlayer setINTs(int ints) {
		super.interceptionsQB += ints;
		return this;
	}
	
	public PassingPlayer setINCs(int incs) {
		super.inc += incs;
		return this;
	}
	
	

	

	@Override
	public String toString() {
		return "PassingPlayer [number=" + this.number + ", comp=" + this.comp + ", compPercent=" + this.compPercent + ", interceptions=" + this.interceptionsQB
				+ ", inc=" + this.inc + ", eventCount=" + this.eventCount + ", yards=" + this.yards + ", avg=" + this.avg + ", tds=" + this.tds
				+ ", longest=" + this.longest + "]";
	}
	
	// turns stats into a list
	public ArrayList<Object> toList() {
		ArrayList<Object> newList = new ArrayList<Object>();
		newList.add(number);
		newList.add(comp);
		newList.add(eventCount);
		newList.add(yards);
		newList.add(compPercent);
		newList.add(avg);
		newList.add(tds);
		newList.add(interceptionsQB);
		newList.add(longest);
		newList.add(inc);
		
		return newList;
		
	}


}
