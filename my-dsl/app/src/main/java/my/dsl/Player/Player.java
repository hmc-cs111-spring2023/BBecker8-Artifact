package my.dsl.Player;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

@JsonIgnoreType
public abstract class Player extends Object{
    // all possible variables for players
	protected final int number; 
	protected int eventCount;
	protected int yards;
	protected double avg;
	protected int tds;
	protected int longest;
	protected int touchBack;
	protected int comp;
	protected int interceptionsQB;
	protected int inc;
	protected int fairCatch;
	protected int madePATs;
	protected int numPATs;
	protected int madeFGs;
	protected int numFGs;
	protected int forLoss;
	protected int sacks;
	protected int interception;
	protected int intRetYards;
	protected int intTds;
	protected int tippass;
	protected int forcedFum;
	protected int recovered;
	protected int fumRetYards;
	protected int fumTds;
	protected int blockedKicks;
	protected int safetys;
	protected double compPercent;
	protected double percentPAT;
	protected double percentFG;
	protected int madeTOT;
	protected int points;
	protected int flYards;
	protected double flAVG;
	
	// general set up 
	
	public Player(int number) {
		this.number = number;
	}
	
	// getters
	
	public Player getPlayer() {
		return this;
	}
	
	public int getYards() {
		return yards;
	}


	public double getAvg() {
		return avg;
	}


	public int getTds() {
		return tds;
	}


	public int getLongest() {
		return longest;
	}

	public int getNumber() {
		return number;
	}



	public int getEventCount() {
		return eventCount;
	}
	
	public int getTouchBack() {
		return touchBack;
	}

	public int getComp() {
		return comp;
	}

	public int getInterceptionsQB() {
		return interceptionsQB;
	}

	public int getInc() {
		return inc;
	}

	public int getFairCatch() {
		return fairCatch;
	}

	public int getMadePATs() {
		return madePATs;
	}

	public int getNumPATs() {
		return numPATs;
	}

	public int getMadeFGs() {
		return madeFGs;
	}

	public int getNumFGs() {
		return numFGs;
	}

	public int getForLoss() {
		return forLoss;
	}

	public int getSacks() {
		return sacks;
	}

	public int getInterception() {
		return interception;
	}

	public int getIntRetYards() {
		return intRetYards;
	}

	public int getIntTds() {
		return intTds;
	}

	public int getTippass() {
		return tippass;
	}

	public int getForcedFum() {
		return forcedFum;
	}

	public int getRecovered() {
		return recovered;
	}

	public int getFumRetYards() {
		return fumRetYards;
	}

	public int getFumTds() {
		return fumTds;
	}

	public int getBlockedKicks() {
		return blockedKicks;
	}

	public int getSafetys() {
		return safetys;
	}
	
	public double getCompPercent() {
		return compPercent;
	}

	public double getPercentPAT() {
		return percentPAT;
	}

	public double getPercentFG() {
		return percentFG;
	}

	public int getMadeTOT() {
		return madeTOT;
	}

	public int getPoints() {
		return points;
	}

	@Override
	public String toString() {
		return "Players [number=" + number + ", eventCount=" + eventCount + ", yards=" + yards
				+ ", avg=" + avg + ", tds=" + tds + ", longest=" + longest + "]";
	}
	

}