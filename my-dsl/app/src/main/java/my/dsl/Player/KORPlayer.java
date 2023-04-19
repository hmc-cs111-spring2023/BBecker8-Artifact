package my.dsl.Player;
import java.util.ArrayList;


public class KORPlayer extends Player{
	
	public KORPlayer(int number) {
		super(number);
		super.eventCount = 0;
		super.yards = 0;
		super.avg = 0.0;
		super.tds = 0;
		super.longest = 0;
		
	}
	
	// setters for the KOR Player for each stat type 
	public KORPlayer updatePlayer() {
		super.eventCount += 1;
		super.avg = ((double) super.yards)/((double)super.eventCount);
		return this;
	}
	
	public KORPlayer setYards(int yards) {
		super.yards += yards;
		if (yards > super.longest) {
			super.longest = yards;
		}
		return this;
	}
	
	public KORPlayer setTDs(String tds) throws Exception {
		if (tds.equals("TD")) {
			super.tds += 1;
		} else if (tds.equals("null")){
			return this;
		} else {
			throw new Exception("Outcome did not match", new Throwable(tds));
		}
		return this;
	}
	
	public KORPlayer setTDs(int tds) {
		super.tds += tds;
		return this;
	}
	
	@Override
	public String toString() {
		return "KORPlayer [number=" + number + ", eventCount=" + eventCount + ", yards=" + yards
				+ ", avg=" + avg + ", tds=" + tds + ", longest=" + longest + "]";
	}
	
	public ArrayList<Object> toList() {
		ArrayList<Object> newList = new ArrayList<Object>();
		newList.add(number);
		newList.add(eventCount);
		newList.add(yards);
		newList.add(avg);
		newList.add(longest);
		newList.add(tds);
		
		return newList;
		
	}

}

