package my.dsl.Player;

import java.util.ArrayList;
import java.util.List;

public class DefensivePlayer extends Player{
	
	public DefensivePlayer(int number) {
		super(number);
		super.forLoss = 0;
		super.flYards = 0;
		super.flAVG = 0.0;
		// TODO keep track of yards? in sacks or in for loss?
		super.sacks = 0;
		super.interception = 0;
		super.intRetYards = 0;
		super.intTds = 0;
		super.tippass = 0;
		super.forcedFum = 0;
		super.recovered = 0;
		super.fumRetYards = 0;
		super.fumTds = 0;
		// TODO keep track of Type?
		super.blockedKicks = 0;
		super.safetys = 0;
		super.points = 0;
	}
	// setters for the defense for each stat type 
	public DefensivePlayer updateDefensivePlayer() {
		super.flAVG = ((double) super.flYards / (double) super.forLoss);
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
		} else {
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
		} else {
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
		if(n.equals("TD") && b.equals("fum")){
			super.fumTds += 1;
			super.recovered += 1;
		} else {
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
		} else {
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
	
	public List<Object> toList() {
		List<Object> newList = new ArrayList<Object>();
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