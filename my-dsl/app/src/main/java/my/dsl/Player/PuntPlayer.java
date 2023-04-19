package my.dsl.Player;
import java.util.ArrayList;

/*
 * class representing a Punt player and their possible stats
 */
public class PuntPlayer extends Player{

	public PuntPlayer(int number) {
		super(number);
		super.eventCount = 0;
		super.yards = 0;
		super.avg = 0.0;
		super.longest = 0;
	}

	// setters for the Punt Player for each stat type 
	
	public PuntPlayer updatePlayer() {
		super.eventCount += 1;
		super.avg = ((double) super.yards)/((double)super.eventCount);
		return this;
	}
	
	public PuntPlayer setYards(int yards) {
		super.yards += yards;
		if (yards > super.longest) {
			super.longest = yards;
		}
		return this;
	}
	
	@Override
	public String toString() {
		return "PuntPlayer [number=" + number + ", eventCount=" + eventCount + ", yards=" + yards
				+ ", avg=" + avg + ", longest=" + longest + "]";
	}

	public ArrayList<Object> toList() {
		ArrayList<Object> newList = new ArrayList<Object>();
		newList.add(number);
		newList.add(eventCount);
		newList.add(yards);
		newList.add(avg);
		newList.add(longest);
		
		return newList;
		
	}
}
