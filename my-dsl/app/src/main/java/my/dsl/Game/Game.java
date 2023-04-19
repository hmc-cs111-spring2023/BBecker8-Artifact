package my.dsl.Game;
import java.util.ArrayList;
import java.util.HashMap;

import my.dsl.Parser.Parser;
import my.dsl.Player.*;

/**
* A class representing an instance of a single football game
*
*/

public class Game {

	private  HashMap<String, ArrayList<Player>> playTypeMap;

    public Game() {
    	this.playTypeMap = new HashMap<String, ArrayList<Player>>();
        
    	playTypeMap.put("Pass", new ArrayList<Player>());
    	playTypeMap.put("Rush", new ArrayList<Player>());
    	playTypeMap.put("Receive", new ArrayList<Player>());
    	playTypeMap.put("Kick", new ArrayList<Player>());
    	playTypeMap.put("Punt", new ArrayList<Player>());
    	playTypeMap.put("FGPAT", new ArrayList<Player>());
    	playTypeMap.put("KOR", new ArrayList<Player>());
    	playTypeMap.put("PuntRet", new ArrayList<Player>());
    	playTypeMap.put("Defense", new ArrayList<Player>());
    }

	@Override
	public String toString() {
		return "Game [playTypeMap=" + playTypeMap + "]";
	}

	public HashMap<String, ArrayList<Player>> getPlayTypeMap() {
		return this.playTypeMap;
	}
	
	// adds a player to the hashmap list at a specified hash
	public boolean add(String playType, Player player) throws Exception {
	    ArrayList<Player> playersList = this.playTypeMap.get(playType);
	    boolean isDuplicate = false;

	    for (Player p : playersList) {
	        if (p.getNumber() == player.getNumber()) {
	        	try {
					update(playType, player, p);
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
	            isDuplicate = true;
	            break;
	        }
	    }

	    if (!isDuplicate) {
	        playersList.add(player);
	    }

	    return !isDuplicate;
	}
	
	// if a player is already in hashmap list then update the players stats
	public void update(String playType, Player player, Player p) throws Exception {
	    // should take attributes from player and add them to p depending on the player type
		switch (playType) {
	    case "Pass":
	        ((PassingPlayer) p).setYards(player.getYards()).setINCs(player.getInc()).setTDs(player.getTds()).setINTs(player.getInterceptionsQB()).updatePlayer();
	        break;
		case "Rush":
			((RushingPlayer) p).setYards(player.getYards()).setTDs(player.getTds()).updatePlayer();
			break;
	    case "Receive":
	    	((ReceivingPlayer) p).setYards(player.getYards()).setTDs(player.getTds()).updatePlayer();
	    	break;
	    case "Kick":
	    	((KickingPlayer) p).setYards(player.getYards()).setTBs(player.getTouchBack()).updatePlayer();
	    	break;
	    case "Punt":
	    	((PuntPlayer) p).setYards(player.getYards()).updatePlayer();
	    	break;
	    case "FGPAT":
	    	((PATFGPlayer) p).setMadePAT(player.getMadePATs()).setNumPAT(player.getNumPATs()).setMadeFG(player.getMadeFGs()).setNumFG(player.getNumFGs()).setLogest(player.getLongest()).updatePlayer();
	    	break;
	    case "KOR":
	    	((KORPlayer) p).setYards(player.getYards()).setTDs(player.getTds()).updatePlayer();
	    	break;
	    case "PuntRet":
	    	((PORPlayer) p).setYards(player.getYards()).setTDs(player.getTds()).setFCs(player.getFairCatch()).updatePlayer();
	    	break;
	    case "Defense":
	    	((DefensivePlayer) p).setForLoss(player.getForLoss()).setSacks(player.getSacks()).setINT(player.getInterception()).setINTRetY(player.getIntRetYards()).setINTTDs(player.getIntTds()).setTippass(player.getTippass()).setFF(player.getForcedFum()).setRecovered(player.getRecovered()).setFumRetY(player.getFumRetYards()).setFumTDs(player.getFumTds()).setBlockedKick(player.getBlockedKicks()).setSafteys(player.getSafetys()).updateDefensivePlayer();
	    	break;
	    default:
	    	Parser parser = new Parser();
	    	System.out.println(parser.isDefensivePlay(playType));
	    	System.out.println(playType);
	    	if (parser.isDefensivePlay(playType)) {
		    	((DefensivePlayer) p).setForLoss(player.getForLoss()).setSacks(player.getSacks()).setINT(player.getInterception()).setINTRetY(player.getIntRetYards()).setINTTDs(player.getIntTds()).setTippass(player.getTippass()).setFF(player.getForcedFum()).setRecovered(player.getRecovered()).setFumRetY(player.getFumRetYards()).setFumTDs(player.getFumTds()).setBlockedKick(player.getBlockedKicks()).setSafteys(player.getSafetys()).updateDefensivePlayer();
		    	break;
	    	} else {
	    		throw new Exception("Playtype did not match", new Throwable(playType));
	    	}
	 
		}
	
	}

	public void setGame(Game game) {
		this.playTypeMap = game.playTypeMap;
		
	}

}