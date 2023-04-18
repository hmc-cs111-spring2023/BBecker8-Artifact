package my.dsl.Player;
import java.util.ArrayList;
import java.util.List;

public class KickingPlayer extends Player{
	//note touchbacks are currently touchdowns
	
	public KickingPlayer(int number) {
		super(number);
		super.eventCount = 0;
		super.yards = 0;
		super.avg = 0.0;
		super.touchBack = 0;
		super.longest = 0;
		super.touchBack = 0;
	}
	
	// setters for the kicking players for each stat type 
	public KickingPlayer updatePlayer() {
		super.eventCount += 1;
		super.avg = ((double) super.yards)/((double)super.eventCount);
		return this;
	}
	
	public KickingPlayer setYards(int yards) {
		super.yards += yards;
		if (yards > super.longest) {
			super.longest = yards;
		}
		return this;
	}
	
	public KickingPlayer setTBs(String tbs) throws Exception {
		if (tbs.equals("TB")) {
			super.touchBack += 1;
		} else if (tbs.equals("null")){
			return this;
		} else {
			throw new Exception("Outcome did not match", new Throwable(tbs));
		}
		return this;
	}
	
	public KickingPlayer setTBs(int tbs) {
		super.touchBack += tbs;
		return this;
	}

	@Override
	public String toString() {
		return "KickingPlayer [number=" + number + ", eventCount=" + eventCount + ", yards=" + yards
				+ ", avg=" + avg + ", longest=" + longest + ", touchBack=" + touchBack + "]";
	}
	
	public ArrayList<Object> toList() {
		ArrayList<Object> newList = new ArrayList<Object>();
		newList.add(number);
		newList.add(eventCount);
		newList.add(yards);
		newList.add(avg);
		newList.add(longest);
		newList.add(touchBack);
		
		return newList;
		
	}


}