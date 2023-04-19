package my.dsl.Player;

import java.util.ArrayList;

/*
 * class representing a defensive player and their possible stats
 */
public class DefensivePlayer extends Player{
	
	public DefensivePlayer(int number) {
		super(number);
		super.forLoss = 0;
		super.flYards = 0;
		super.flAVG = 0.0;
		super.sacks = 0;
		super.interception = 0;
		super.intRetYards = 0;
		super.intTds = 0;
		super.tippass = 0;
		super.forcedFum = 0;
		super.recovered = 0;
		super.fumRetYards = 0;
		super.fumTds = 0;
		super.blockedKicks = 0;
		super.safetys = 0;
		super.points = 0;
	}

	// setters for the defense for each stat type 
	public DefensivePlayer updateDefensivePlayer() {
		if ( ((double) super.flYards / (double) super.forLoss) > 0.0) {
			super.flAVG = ((double) super.flYards / (double) super.forLoss);
		} else {
			super.flAVG = 0.0;
		}
		
		super.points = (6*super.intTds) + (6*super.fumTds) + (2*super.safetys);
		return this;
	}
	
	public DefensivePlayer setForLoss(int n) {
		super.forLoss += n;
		return this;
	}
	public DefensivePlayer setForLossY(int n) {
		super.flYards += n;
		return this;
	}
	
	public DefensivePlayer setSacks(int n) {
//		super.forLoss += n;
		super.sacks += n;
		return this;
	}
	public DefensivePlayer setINT(int n) {
		super.interception += n;
		return this;
	}
	
	public DefensivePlayer setINTRetY(int n) {
		super.intRetYards += n;
		return this;
	}
	public DefensivePlayer setINTTDs(int n) {
		super.intTds += n;
		return this;
	}
	public DefensivePlayer setINTTDs(String n, String b) throws Exception {
		if(n.equals("TD") && b.equals("int")){
			super.intTds += 1;
		} else if (n.equals("null")){
			return this;
		}else {
			throw new Exception("Outcome did not match", new Throwable(n));
		}
		return this;
	}
	public DefensivePlayer setTippass(int n) {
		super.tippass += n;
		return this;
	}
	public DefensivePlayer setFF(int n) {
		super.forcedFum += n;
		return this;
	}
	public DefensivePlayer setRecovered(int n) {
		super.recovered += n;
		return this;
	}
	public DefensivePlayer setRecovered(String n) throws Exception {
		if(n.equals("REC")){
			super.recovered += 1;
		} else if (n.equals("null") || n.equals("TD")){
			return this;
		}else {
			throw new Exception("Outcome did not match", new Throwable(n));
		}
		return this;
	}
	public DefensivePlayer setFumTDs(int n) {
		super.fumTds += n;
		super.recovered += n;
		return this;
	}
	public DefensivePlayer setFumTDs(String n, String b) throws Exception {
		if(n.equals("TD") && (b.equals("fum") || b.equals("ff"))){
			super.fumTds += 1;
			super.recovered += 1;
		} else if (n.equals("null") || n.equals("REC")){
			return this;
		}else {
			throw new Exception("Outcome did not match", new Throwable(n));
		}
		return this;
	}
	
	public DefensivePlayer setFumRetY(int n) {
		super.fumRetYards += n;
		return this;
	}
	public DefensivePlayer setBlockedKick(int n) {
		super.blockedKicks += n;
		return this;
	}
	public DefensivePlayer setBlockedKick(String n) throws Exception {
		if(n.equals("FG") || n.equals("PAT") || n.equals("PUNT")){
			super.blockedKicks += 1;
		} else if (n.equals("null")){
			return this;
		}else {
			throw new Exception("Outcome did not match", new Throwable(n));
		}
		return this;
	}
	public DefensivePlayer setSafteys(int n) {
		super.safetys += n;
		return this;
	}
	
	@Override
	public String toString() {
		return "DefensivePlayer [number=" + number + ", forLoss=" + forLoss + ", sacks=" + sacks + ", interception=" + interception
				+ ", intRetYards=" + intRetYards + ", intTds=" + intTds + ", tippass=" + tippass + ", forcedFum="
				+ forcedFum + ", recovered=" + recovered + ", fumRetYards=" + fumRetYards + ", fumTds=" + fumTds
				+ ", blockedKicks=" + blockedKicks + ", safetys=" + safetys + ", points=" + points + ", flYards="
				+ flYards + ", flAVG=" + flAVG + "]";
	}
	// turns stats into a list 
	public ArrayList<Object> toList() {
		ArrayList<Object> newList = new ArrayList<Object>();
		newList.add(number);
		newList.add(forLoss);
		newList.add(flYards);
		newList.add(flAVG);
		newList.add(sacks);
		newList.add(interception);
		newList.add(intRetYards);
		newList.add(intTds);
		newList.add(tippass);
		newList.add(forcedFum);
		newList.add(recovered);
		newList.add(fumRetYards);
		newList.add(fumTds);
		newList.add(blockedKicks);
		newList.add(safetys);
		newList.add(points);
		
		return newList;
		
	}

}
