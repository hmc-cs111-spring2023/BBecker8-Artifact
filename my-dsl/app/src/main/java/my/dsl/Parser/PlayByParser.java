package my.dsl.Parser;

/*
* Class for parsing input data and returns a play by play string
*
 */
public class PlayByParser {

	// Parses each input string type and adds the correct input to each field within a specified game.
	protected String parse(String playtype, String playerNum, String numYards, String playerNum2, String outcome) throws Exception {
	
		if (isPassPlay(playtype)) {	
			return pass(playerNum, numYards, playerNum2, outcome);
		}
		
		else if (isRunPlay(playtype)) {
			return run(playerNum, numYards, outcome);
		}
		
		else if (isKickPlay(playtype)) {
			return kick(playerNum, numYards, outcome);
		}
		
		else if (isPuntPlay(playtype)) {
			return punt(playerNum, numYards);
		}
		
		else if (isPATPlay(playtype)) {
			return pat(playerNum, numYards, outcome);
		}
		
		else if (isFGPlay(playtype)) {
			return fg(playerNum, numYards, outcome);
		}
		
		else if (isKORPlay(playtype)) {
			return kor(playerNum, numYards, outcome);
		}
		
		else if (isPORPlay(playtype)) {
			return por(playerNum, numYards, outcome);
		}
		
		else if (isDefensivePlay(playtype)) {
			return defense(playtype, playerNum, numYards, outcome);
		}
		else {
			throw new Exception("Playtype not found", new Throwable(playtype));
		}
		
	}
	
	// sets the stats from each field to the provided game for each play type
	private String pass(String playerNum, String numYards, String playerNum2, String outcome) throws Exception {
		String stringPass = "Player " + playerNum + " threw";
		String stringRec = "";
		if (!numYards.equals("null")) {
			stringPass += " for " + numYards;
		}
		if (!playerNum2.equals("null")) {
			stringPass += " to Player " + playerNum2;
			if (!outcome.equals("null")) {
				stringRec = "Player " + playerNum2 + " caught a " + numYards + " pass from Player " + playerNum + " and the result was a " + outcome;
			}
			stringRec = "Player " + playerNum2 + " caught a " + numYards + " pass from Player " + playerNum;

		}
		if (!outcome.equals("null")) {
			stringPass += " and the result was a " + outcome;
		}
		return stringPass + "\n" + stringRec;
	}
	
	private String run(String playerNum, String numYards, String outcome) throws Exception {
		String string = "Player " + playerNum + " rushed";
		if (!numYards.equals("null")) {
			string += " for " + numYards;
		}
		if (!outcome.equals("null")) {
			string += " and the result was a " + outcome;
		}
		return string;
	}
	
	private String kick(String playerNum, String numYards, String outcome) throws Exception {
		String string = "Player " + playerNum + " kicked off";
		if (!numYards.equals("null")) {
			string += " for " + numYards;
		}
		if (!outcome.equals("null")) {
			string += " and the result was a " + outcome;
		}
		return string;
	}
	
	private String punt(String playerNum, String numYards) throws Exception {
		String string = "Player " + playerNum + " Punted";
		if (!numYards.equals("null")) {
			string += " for " + numYards;
		}
		return string;
	}
	
	private String pat(String playerNum, String numYards, String outcome) throws Exception {
		String string = "Player " + playerNum + " kicked a PAT";
		if (!numYards.equals("null")) {
			string += " for " + numYards;
		}
		if (!outcome.equals("null")) {
			string += " and the result was a " + outcome;
		}
		return string;
	}
	
	private String fg(String playerNum, String numYards, String outcome) throws Exception {
		String string = "Player " + playerNum + " kicked a FG";
		if (!numYards.equals("null")) {
			string += " for " + numYards;
		}
		if (!outcome.equals("null")) {
			string += " and the result was a " + outcome;
		}
		return string;
	}
	
	private String kor(String playerNum, String numYards, String outcome) throws Exception {
		String string = "Player " + playerNum + " Returned A Kick";
		if (!numYards.equals("null")) {
			string += " for " + numYards;
		}
		if (!outcome.equals("null")) {
			string += " and the result was a " + outcome;
		}
		return string;
	}
	
	private String por(String playerNum, String numYards, String outcome) throws Exception {
		String string = "Player " + playerNum + " Returned A Punt";
		if (!numYards.equals("null")) {
			string += " for " + numYards;
		}
		if (!outcome.equals("null")) {
			string += " and the result was a " + outcome;
		}
		return string;
	}
	
	private String defense(String playtype, String playerNum, String numYards, String outcome) throws Exception {
		playtype = playtype.toLowerCase();
		if (playtype.equals("forloss") || playtype.equals("fl")) {
			String string = "Player " + playerNum + " got a Tackle for loss";
			if (!numYards.equals("null")) {
				string += " of " + numYards;
			}
			return string;
		}
		else if (playtype.equals("sack") || playtype.equals("sck")) {
			if (toInt(parseNumber(numYards)) < 0) {
				String string = "Player " + playerNum + " got Sacked";
				if (!numYards.equals("null")) {
					string += " for " + numYards;
				}
				return string;
			} else {
				String string = "Player " + playerNum + " got a Sack";
				if (!numYards.equals("null")) {
					string += " for " + numYards;
				}
				return string;
			}
		}
		else if (playtype.equals("int")) {
			String string = "Player " + playerNum + " got an Interception";
			if (!numYards.equals("null")) {
				string += " for " + numYards;
			}
			if (!outcome.equals("null")) {
				string += " and the result was a " + outcome;
			}
			return string;
		}
		else if (playtype.equals("tipppass") || playtype.equals("tp") || playtype.equals("tipp")) {
			return "Player " + playerNum + " got a tipped pass";
		}
		else if (playtype.equals("fum") || playtype.equals("ff")) {
			String string = "Player " + playerNum;
			if (!outcome.equals("null")) {
				if (outcome.equals("NREC")){
					string += " forced a fumble";
				}
				if (outcome.equals("REC")){
					string += " recovered a fumble";
				}
				if (outcome.equals("TD")){
					string += " forced a fumble and it was recovered for a " + outcome;
				}
			}
			return string;
		}
		else if (playtype.equals("blockkick") || playtype.equals("bk") || playtype.equals("bkick")) {
			String string = "Player " + playerNum + " blocked a ";
			if (!outcome.equals("null")) {
				string += outcome;
			}
			return string;
		}
		else if (playtype.equals("safety") || playtype.equals("sfty")) {
			return "Player " + playerNum + " got a Safety";
		} else {
			throw new Exception("Defensive Playtype not found", new Throwable(playtype));
		}
	}
	
	
	// // turns an object to an integer
	public int toInt(Object n) {
		if (n instanceof Integer) {
			return (int) n;
		}
		return 0;
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
	    
	    // Check if the input matches any of the PAT variations
	   
        if (input.equals("pat")) {
            return true;
        }
	    
	    // If the input does not match any PAT variations, it is not a PAT play
     
        return false;
	}
	
	public boolean isRunPlay(String input)  {
	    // Convert the input to lowercase for case-insensitive matching
	    input = input.toLowerCase();
	    
	    // Define an array of strings that represent variations of the word "run"
	    String[] runVariations = {"run", "r", "rush"};
	    
	    // Check if the input matches any of the Run variations
	    for (String run : runVariations) {
	        if (input.equals(run)) {
	            return true;
	        }
	    }
	    
	    // If the input does not match any Run variations, it is not a Run play
	    return false;
	}
	
	public boolean isFGPlay(String input) {
	    // Convert the input to lowercase for case-insensitive matching
	    input = input.toLowerCase();
	    
	    // Check if the input matches any of the FG variations
        if (input.equals("fg")) {
            return true;
        }
	    
	    // If the input does not match any FG variations, it is not a FG play
        return false;
	}
	
	private boolean isKickPlay(String input) {
	    // Convert the input to lowercase for case-insensitive matching
	    input = input.toLowerCase();
	    
	    // Define an array of strings that represent variations of the word "Kick"
	    String[] kickVariations = {"kick", "k"};
	    
	    // Check if the input matches any of the Kick variations
	    for (String kick : kickVariations) {
	        if (input.equals(kick)) {
	            return true;
	        }
	    }
	    
	    // If the input does not match any Kick variations, it is not a Kick play
	    return false;
	}
	
	private boolean isPuntPlay(String input) {
	    // Convert the input to lowercase for case-insensitive matching
	    input = input.toLowerCase();
	    
	    // Define an array of strings that represent variations of the word "Punt"
	    String[] puntVariations = {"punt", "pt"};
	    
	    // Check if the input matches any of the Punt variations
	    for (String punt : puntVariations) {
	        if (input.equals(punt)) {
	            return true;
	        }
	    }
	    
	    // If the input does not match any Punt variations, it is not a Punt play
	    return false;
	}
	
	public boolean isKORPlay(String input) {
	    // Convert the input to lowercase for case-insensitive matching
	    input = input.toLowerCase();
	    
	    // Check if the input matches any of the KOR variations
        if (input.equals("kor")) {
            return true;
        }
	    
	    // If the input does not match any KOR variations, it is not a KOR play
        return false;
	}
	
	private boolean isPORPlay(String input) {
	    // Convert the input to lowercase for case-insensitive matching
	    input = input.toLowerCase();
	    
	    // Define an array of strings that represent variations of the word "POR"
	    String[] puntRVariations = {"por", "pr"};
	    
	    // Check if the input matches any of the POR variations
	    for (String puntR : puntRVariations) {
	        if (input.equals(puntR)) {
	            return true;
	        }
	    }
	    
	    // If the input does not match any POR variations, it is not a POR play
	    return false;
	}
	
	public boolean isDefensivePlay(String input) {
	    // Convert the input to lowercase for case-insensitive matching
	    input = input.toLowerCase();
	    
	    // Define an array of strings that represent variations of defensive words
	    String[] defenseVariations = {"forloss", "fl", "sack", "sck", "int", "tippass", "tp", "tipp", "fum", "ff", "ffum", "forcedf", "blockedk", "bk", "bkick", "safety", "sfty"};
	    
	    // Check if the input matches any of the Defensive variations
	    for (String defense : defenseVariations) {
	        if (input.equals(defense)) {
	            return true;
	        }
	    }
	    
	    // If the input does not match any Defensive variations, it is not a Defensive play
	    return false;
	}
	
	// parses the name or a number from a string input. Useful for all number related fields
	public Object parseNumber(String input) throws Exception {
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
