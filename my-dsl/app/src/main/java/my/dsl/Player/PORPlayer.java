package my.dsl.Player;
import java.util.ArrayList;
import java.util.List;

public class PORPlayer extends Player{
	
	public PORPlayer(int number) {
		super(number);
		super.eventCount = 0;
		super.yards = 0;
		super.avg = 0.0;
		super.tds = 0;
		super.longest = 0;
		super.fairCatch = fairCatch;
	}
	
	// setters for the POR Player for each stat type 
	
	public PORPlayer updatePlayer() {
		super.eventCount += 1;
		super.avg = ((double) super.yards)/((double)super.eventCount);
		return this;
	}
	
	public PORPlayer setYards(int yards) {
		super.yards += yards;
		if (yards > super.longest) {
			super.longest = yards;
		}
		return this;
	}
	
	public PORPlayer setTDs(String tds) throws Exception {
		if (tds.equals("TD")) {
			super.tds += 1;
		} else if (tds.equals("null")){
			return this;
		} else {
			throw new Exception("Outcome did not match", new Throwable(tds));
		}
		return this;
	}
	
	public PORPlayer setTDs(int tds) {
		super.tds += tds;
		return this;
	}
	
	public PORPlayer setFCs(String fc) throws Exception {
		if (fc.equals("FC")) {
			super.fairCatch += 1;
		} else if (fc.equals("null")){
			return this;
		} else {
			throw new Exception("Outcome did not match", new Throwable(fc));
		}
		return this;
	}
	
	public PORPlayer setFCs(int fc) {
		super.fairCatch += fc;
		return this;
	}
	
	@Override
	public String toString() {
		return "PORPlayer [number=" + number + ", eventCount=" + eventCount + ", yards=" + yards
				+ ", avg=" + avg + ", tds=" + tds + ", longest=" + longest + ", fairCatch=" + fairCatch + "]";
	}
	
	public List<Object> toList() {
		List<Object> newList = new ArrayList<Object>();
		newList.add(number);
		newList.add(eventCount);
		newList.add(yards);
		newList.add(avg);
		newList.add(longest);
		newList.add(fairCatch);
		
		return newList;
		
	}
	
}

