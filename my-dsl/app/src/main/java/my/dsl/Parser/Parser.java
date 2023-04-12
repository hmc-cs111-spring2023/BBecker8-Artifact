package my.dsl.Parser;

import my.dsl.Game.*;
import my.dsl.Player.*;

public class Parser {
	// build parser for instance when playtype is "P" or "Pass" use game1.add("Pass", new PassingPlayer(11, "Kaden", 0, 0, 0, 0, 0, 0, 0, 0, 0).setPlayer(10, 2, 1, 1, 1));
				// passing in and converting number values from string to int ect...
				// string to int
				// outcome methods INT INC FUM ...
				// pass passplayer (deals with  one or two players/stat types if 2) P Pass pass
				// run runplayer R Run run
				// PAT patfgplayer PAT pat Pat 
				// FG patfgplayer FG fg Fg
				// kick kickplayer Kick kick KICK 
				// punt puntplayer Punt punt PUNT
				// KOR korplayer KOR kor Kor
				// POR porplayer POR  Por por PR pr Pr
				
				// sack offense runplayer Sack sack SACK sk
				// penalty extrastat 
				// tackle defenseplayer tackle Tackle Tkl TKL tkl
				// tfl defenseplayer TFL Tfl tfl
				// sack defense defenseplayer Sack sack SACK sk
				// fum defenseplayer Fumble fumble fum Fum FUM
				// int defenseplayer interception Interception INT int
				// tippass defenseplayer tip Tip TipP TP 
				// blocked kick  defenseplayer BK BKick blkck
				// saftey defenseplayer sfty sft 
	
	// Parses each input string type and adds the correct input to each field within a specified game.
	protected void parse(String playtype, String playerNum, String numYards, String playerNum2, String outcome, Game game) throws Exception {
	
		if (isPassPlay(playtype)) {	
			pass(game, playtype, playerNum, numYards, playerNum2, outcome);
			System.out.println(game);
		}
		
		else if (isRunPlay(playtype)) {
			run(game, playtype, playerNum, numYards, playerNum2, outcome);
			System.out.println(game);
		}
		
		else if (isKickPlay(playtype)) {
			kick(game, playtype, playerNum, numYards, playerNum2, outcome);
			System.out.println(game);
		}
		
		else if (isPuntPlay(playtype)) {
			punt(game, playtype, playerNum, numYards, playerNum2, outcome);
			System.out.println(game);
		}
		
		else if (isPATPlay(playtype)) {
			pat(game, playtype, playerNum, numYards, playerNum2, outcome);
			System.out.println(game);
		}
		
		else if (isFGPlay(playtype)) {
			fg(game, playtype, playerNum, numYards, playerNum2, outcome);
			System.out.println(game);
		}
		
		else if (isKORPlay(playtype)) {
			kor(game, playtype, playerNum, numYards, playerNum2, outcome);
			System.out.println(game);
		}
		
		else if (isPORPlay(playtype)) {
			por(game, playtype, playerNum, numYards, playerNum2, outcome);
			System.out.println(game);
		}
		
		else if (isDefensivePlay(playtype)) {
			defense(game, playtype, playerNum, numYards, playerNum2, outcome);
			System.out.println(game);
		}
		
		else if (isSackPlay(playtype)) {
			if(toInt(parseNumber(numYards)) < 0) {
				run(game, playtype, playerNum, numYards, playerNum2, outcome);
			} else {
				defense(game, playtype, playerNum, numYards, playerNum2, outcome);
			}
			System.out.println(game);
		} else {
			throw new Exception("Playtype not found", new Throwable(playtype));
		}
		
	}
	
	// sets the stats from each field to the provided game for each play type
	private Game pass(Game game, String playtype, String playerNum, String numYards, String playerNum2, String outcome) throws Exception {
		game.add("Pass", new PassingPlayer(toInt(parseNumber(playerNum))).setYards(toInt(parseNumber(numYards))).setINCs(outcome).setTDs(outcome).setINTs(outcome).updatePlayer());
		
		if (playerNum2 != "null") {
			game.add("Receive", new ReceivingPlayer(toInt(parseNumber(playerNum2)))
					 .setYards(toInt(parseNumber(numYards))).setTDs(outcome).updatePlayer());
		}
		return game;
	}
	
	private Game run(Game game, String playtype, String playerNum, String numYards, String playerOrNum2, String outcome) throws Exception {
		game.add("Rush", new RushingPlayer(toInt(parseNumber(playerNum))).setYards(toInt(parseNumber(numYards))).setTDs(outcome).updatePlayer());
		
		return game;
	}
	
	private Game kick(Game game, String playtype, String playerNum, String numYards, String playerNum2, String outcome) throws Exception {
		game.add("Kick", new KickingPlayer(toInt(parseNumber(playerNum))).setYards(toInt(parseNumber(numYards))).setTBs(outcome).updatePlayer());
		return game;
	}
	
	private Game punt(Game game, String playtype, String playerNum, String numYards, String playerNum2, String outcome) throws Exception {
		game.add("Punt", new PuntPlayer(toInt(parseNumber(playerNum))).setYards(toInt(parseNumber(numYards))).updatePlayer());
		return game;
	}
	
	private Game pat(Game game, String playtype, String playerNum, String numYards, String playerNum2, String outcome) throws Exception {
		game.add("FGPAT", new PATFGPlayer(toInt(parseNumber(playerNum))).setLogest(toInt(parseNumber(numYards))).setNumPAT(1).setMadePAT(outcome).updatePlayer());
		return game;
	}
	
	private Game fg(Game game, String playtype, String playerNum, String numYards, String playerNum2, String outcome) throws Exception {
		game.add("FGPAT", new PATFGPlayer(toInt(parseNumber(playerNum))).setLogest(toInt(parseNumber(numYards))).setNumFG(1).setMadeFG(outcome).updatePlayer());
		return game;
	}
	
	private Game kor(Game game, String playtype, String playerNum, String numYards, String playerNum2, String outcome) throws Exception {
		game.add("KOR", new KORPlayer(toInt(parseNumber(playerNum))).setYards(toInt(parseNumber(numYards))).setTDs(outcome).updatePlayer());
		return game;
	}
	
	private Game por(Game game, String playtype, String playerNum, String numYards, String playerNum2, String outcome) throws Exception {
		game.add("PuntRet", new PORPlayer(toInt(parseNumber(playerNum))).setYards(toInt(parseNumber(numYards))).setTDs(outcome).setFCs(outcome).updatePlayer());
		return game;
	}
	
	private Game defense(Game game, String playtype, String playerNum, String numYards, String playerNum2, String outcome) throws Exception {
		playtype = playtype.toLowerCase();
		if (playtype.equals("forloss") || playtype.equals("fl")) {
			game.add("Defense", new DefensivePlayer(toInt(parseNumber(playerNum))).setForLoss(1).setForLossY(toInt(parseNumber(numYards))).updateDefensivePlayer());
		}
		else if (playtype.equals("sack")) {
			game.add("Defense", new DefensivePlayer(toInt(parseNumber(playerNum))).setSacks(1).updateDefensivePlayer());
		}
		else if (playtype.equals("int")) {
			game.add("Defense", new DefensivePlayer(toInt(parseNumber(playerNum))).setINT(1).setINTRetY(toInt(parseNumber(numYards))).setINTTDs(outcome, playtype).updateDefensivePlayer());
		}
		else if (playtype.equals("tipppass") || playtype.equals("tp") || playtype.equals("tipp")) {
			game.add("Defense", new DefensivePlayer(toInt(parseNumber(playerNum))).setTippass(1).updateDefensivePlayer());
		}
		else if (playtype.equals("fum") || playtype.equals("ff")) {
			game.add("Defense", new DefensivePlayer(toInt(parseNumber(playerNum))).setFF(1).setRecovered(outcome).setFumRetY(toInt(parseNumber(numYards))).setFumTDs(outcome,playtype).updateDefensivePlayer());
		}
		else if (playtype.equals("blockkick") || playtype.equals("bk") || playtype.equals("bkick")) {
			game.add("Defense", new DefensivePlayer(toInt(parseNumber(playerNum))).setBlockedKick(outcome).updateDefensivePlayer());
		}
		else if (playtype.equals("safety") || playtype.equals("sfty")) {
			game.add("Defense", new DefensivePlayer(toInt(parseNumber(playerNum))).setSafteys(1).updateDefensivePlayer());
		} else {
			throw new Exception("Defensive Playtype not found", new Throwable(playtype));
		}
		return game;
	}
	
	// turns an object to a string 
	// private String toString(Object n) {
	// 	if (n instanceof String) {
	// 		return (String) n;
	// 	}
	// 	return "what";
	// }
	
	// turns an object to an integer
	private int toInt(Object n) {
		if (n instanceof Integer) {
			return (int) n;
		}
		return 0;
	}
	
	// determines the type of play given the playtype string for each play type
	// takes into consideration each variation of a user input
	// TODO may look for a separate parser to do same job
	private boolean isSackPlay(String input) {
		// Convert the input to lowercase for case-insensitive matching
	    input = input.toLowerCase();
	    
	    // Define an array of strings that represent variations of the word "pass"
	    String[] sackVariations = {"sack", "sck"};
	    
	    // Check if the input matches any of the pass variations
	    for (String sack : sackVariations) {
	        if (input.equals(sack)) {
	            return true;
	        }
	    }
	    
	    // If the input does not match any pass variations, it is not a pass play
	    return false;
	}
	
	private boolean isPassPlay(String input) {
	    // Convert the input to lowercase for case-insensitive matching
	    input = input.toLowerCase();
	    
	    // Define an array of strings that represent variations of the word "pass"
	    String[] passVariations = {"pass", "p"};
	    
	    // Check if the input matches any of the pass variations
	    for (String pass : passVariations) {
	        if (input.equals(pass)) {
	            return true;
	        }
	    }
	    
	    // If the input does not match any pass variations, it is not a pass play
	    return false;
	}
	
	private boolean isPATPlay(String input) {
	    // Convert the input to lowercase for case-insensitive matching
	    input = input.toLowerCase();
	    
	    // Check if the input matches any of the pass variations
	   
        if (input.equals("pat")) {
            return true;
        }
	    
	    // If the input does not match any pass variations, it is not a pass play
     
        return false;
	}
	
	public boolean isRunPlay(String input)  {
	    // Convert the input to lowercase for case-insensitive matching
	    input = input.toLowerCase();
	    
	    // Define an array of strings that represent variations of the word "run"
	    String[] runVariations = {"run", "r", "rush"};
	    
	    // Check if the input matches any of the pass variations
	    for (String run : runVariations) {
	        if (input.equals(run)) {
	            return true;
	        }
	    }
	    
	    // If the input does not match any pass variations, it is not a pass play
	    return false;
	}
	
	public boolean isFGPlay(String input) {
	    // Convert the input to lowercase for case-insensitive matching
	    input = input.toLowerCase();
	    
	    // Check if the input matches any of the pass variations
        if (input.equals("fg")) {
            return true;
        }
	    
	    // If the input does not match any pass variations, it is not a pass play
        return false;
	}
	
	private boolean isKickPlay(String input) {
	    // Convert the input to lowercase for case-insensitive matching
	    input = input.toLowerCase();
	    
	    // Define an array of strings that represent variations of the word "pass"
	    String[] kickVariations = {"kick", "k"};
	    
	    // Check if the input matches any of the pass variations
	    for (String kick : kickVariations) {
	        if (input.equals(kick)) {
	            return true;
	        }
	    }
	    
	    // If the input does not match any pass variations, it is not a pass play
	    return false;
	}
	
	private boolean isPuntPlay(String input) {
	    // Convert the input to lowercase for case-insensitive matching
	    input = input.toLowerCase();
	    
	    // Define an array of strings that represent variations of the word "pass"
	    String[] puntVariations = {"punt", "pt"};
	    
	    // Check if the input matches any of the pass variations
	    for (String punt : puntVariations) {
	        if (input.equals(punt)) {
	            return true;
	        }
	    }
	    
	    // If the input does not match any pass variations, it is not a pass play
	    return false;
	}
	
	public boolean isKORPlay(String input) {
	    // Convert the input to lowercase for case-insensitive matching
	    input = input.toLowerCase();
	    
	    // Check if the input matches any of the pass variations
        if (input.equals("kor")) {
            return true;
        }
	    
	    // If the input does not match any pass variations, it is not a pass play
        return false;
	}
	
	private boolean isPORPlay(String input) {
	    // Convert the input to lowercase for case-insensitive matching
	    input = input.toLowerCase();
	    
	    // Define an array of strings that represent variations of the word "pass"
	    String[] puntRVariations = {"por", "pr"};
	    
	    // Check if the input matches any of the pass variations
	    for (String puntR : puntRVariations) {
	        if (input.equals(puntR)) {
	            return true;
	        }
	    }
	    
	    // If the input does not match any pass variations, it is not a pass play
	    return false;
	}
	
	private boolean isDefensivePlay(String input) {
	    // Convert the input to lowercase for case-insensitive matching
	    input = input.toLowerCase();
	    
	    // Define an array of strings that represent variations of defensive words
	    String[] defenseVariations = {"forloss", "fl", "sack", "sck", "int", "tippass", "tp", "tipp", "fum", "ff", "ffum", "forcedf", "blockedk", "bk", "bkick", "safety", "sfty"};
	    
	    // Check if the input matches any of the pass variations
	    for (String defense : defenseVariations) {
	        if (input.equals(defense)) {
	            return true;
	        }
	    }
	    
	    // If the input does not match any pass variations, it is not a pass play
	    return false;
	}
	
	// parses the name or a number from a string input. USeful for all number related fields
	
	private static Object parseNumber(String input) throws Exception {
		if (input == "null") {
	        return "null";
	    }
	    // Remove any spaces and convert the input to lowercase
	    input = input.trim().toLowerCase();
	   
	    
	    // Check if the input starts with a negative sign
	    boolean isNegative = false;
	    if (input.startsWith("-")) {
	        isNegative = true;
	        input = input.substring(1); // Remove the negative sign from the input
	    }
	    
	    // Remove any trailing "y" characters
	    if (input.endsWith("y")) {
	        input = input.substring(0, input.length() - 1);
	    }
	    
	    // Parse the input as an integer
	    // TODO number not found try catch
	    try {
	    	int number = Integer.parseInt(input);
	    	// If the input was negative, make the number negative
		    if (isNegative) {
		        number = -number;
		    }
		    // ask for players name?
		    return number;
		} catch (Exception e) {
			throw new Exception("Number not found from input", e);
		}
	    
	}


}
