package my.dsl.Parser;

import java.util.function.Function;
import java.util.regex.*;

import my.dsl.Game.*;

public class StringGrouping {
	
	private static final Pattern STRING_INPUT_PATTERN = Pattern.compile("^(?<playtype>\\w+)\\s?(?<playerNumber>[0-9][0-9]*[^a-z])?\\s?(?<numyards>-?\\d+y)?\\s?(?<playerNumber2>\\d+)?(?:\\s(?<outcome>[A-Z]+))?\\s?$");
	
	
	private String playType;
	private String playerNum;
	private String playerNum2;
	private String numYards;
	private String outcome;
	
	
	
	public StringGrouping() {
        this.playType = null;
    	this.playerNum = null;
    	this.playerNum2 = null;
    	this.numYards = null;
    	this.outcome = null;
        
	}
	
	// Determines if a string is matched by the matcher and sets grouping of string accordingly
	// sends game and each string to the parser
	public void update(String input, Game game) throws Exception {
		Function<String, String> nullToValue = (a) -> a == null ? "null" : a;
		
		Matcher groupsMatcher = STRING_INPUT_PATTERN.matcher(input);
		
		if(groupsMatcher.matches()) {
			
			this.playType = nullToValue.apply(groupsMatcher.group("playtype"));
			this.playerNum = nullToValue.apply(groupsMatcher.group("playerNumber"));
			this.playerNum2 = nullToValue.apply(groupsMatcher.group("playerNumber2"));
			this.numYards = nullToValue.apply(groupsMatcher.group("numyards"));
			this.outcome = nullToValue.apply(groupsMatcher.group("outcome"));
			
			System.out.println("playType=" + playType + ", playerNum=" + playerNum + ", numYards=" + numYards + ", playerNum2=" + playerNum2 + ", outcome=" + outcome + ""); 
			try {
				new Parser().parse(playType, playerNum, numYards, playerNum2, outcome, game);
			} catch (Exception e) {
				throw e;
			}
		} else {
			throw new Exception("Input Format of DSL is Not Valid", new Throwable(input));
		}
		
	}

	//getters

	public String getPlayType() {
		return playType;
	}



	public String getPlayerOrNum() {
		return playerNum;
	}


	public String getPlayerOrNum2() {
		return playerNum2;
	}



	public String getNumYards() {
		return numYards;
	}



	public String getOutcome() {
		return outcome;
	}
	
}
