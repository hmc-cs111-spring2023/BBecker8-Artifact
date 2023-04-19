package my.dsl.API;

import my.dsl.Game.Game;
import my.dsl.Player.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
* Api class for updating and sending requesting to spreadsheets.com
*
*/
public class SpreadsheetApi {
	private static final String KEY_STRING = "ar0wyM-.uMt0YfH8TpeyvEQD7p3sTg";
	private String worksheetcode;
	private String URI_STRING;

	// Build headers for each playType to be passed into the sheet
	@SuppressWarnings("serial")
	private static final ArrayList<Object> PASSING_HEADER_STRINGS = new ArrayList<Object>() {
		{
			add("#");
			add("Comp");
			add("Att");
			add("Yards");
			add("C %");
			add("AVG");
			add("TD");
			add("INT");
			add("Long");
			add("INC");
		}
	};
	// Build headers for each playType to be passed into the sheet
	@SuppressWarnings("serial")
	private static final ArrayList<Object> RUSHING_HEADER_STRINGS = new ArrayList<Object>(){
		{
			add("#");
			add("Carries");
			add("Yards");
			add("AVG");
			add("Long");
			add("TD");
		}
	};
	// Build headers for each playType to be passed into the sheet
	@SuppressWarnings("serial")
	private static final ArrayList<Object> RECEIVING_HEADER_STRINGS = new ArrayList<Object>(){
		{
			add("#");
			add("Receptions");
			add("Yards");
			add("AVG");
			add("Long");
			add("TD");
		}
	};
	// Build headers for each playType to be passed into the sheet
	@SuppressWarnings("serial")
	private static final ArrayList<Object> PATFG_HEADER_STRINGS = new ArrayList<Object>(){
		{
			add("#");
			add("# Kicked");
			add("PAT");
			add("Attempts");
			add("%");
			add("FG");
			add("Attempts");
			add("%");
			add("Long");
			add("Total Made");
			add("Points");
		}
	};
	// Build headers for each playType to be passed into the sheet
	@SuppressWarnings("serial")
	private static final ArrayList<Object> KICK_HEADER_STRINGS = new ArrayList<Object>(){
		{
			add("#");
			add("KO's");
			add("Yards");
			add("AVG");
			add("Long");
			add("TB");
		}
	};
	// Build headers for each playType to be passed into the sheet
	@SuppressWarnings("serial")
	private static final ArrayList<Object> PUNT_HEADER_STRINGS = new ArrayList<Object>(){
		{
			add("#");
			add("P's");
			add("Yards");
			add("AVG");
			add("Long");
		}
	};
	// Build headers for each playType to be passed into the sheet
	@SuppressWarnings("serial")
	private static final ArrayList<Object> KOR_HEADER_STRINGS = new ArrayList<Object>(){
		{
			add("#");
			add("Rets");
			add("Yards");
			add("AVG");
			add("Long");
			add("TD");
		}
	};
	// Build headers for each playType to be passed into the sheet
	@SuppressWarnings("serial")
	private static final ArrayList<Object> POR_HEADER_STRINGS = new ArrayList<Object>(){
		{
			add("#");
			add("Rets");
			add("Yards");
			add("AVG");
			add("Long");
			add("FC");
			add("TD");
		}
	};
	// Build headers for each playType to be passed into the sheet
	@SuppressWarnings("serial")
	private static final ArrayList<Object> DEFENSIVE_HEADER_STRINGS = new ArrayList<Object>(){
		{
			add("#");
			add("For Loss");
			add("FL Yards");
			add("FL AVG");
			add("Sacks");
			add("Int");
			add("Int Yards");
			add("Int TDs");
			add("Tipped Pass");
			add("Forced Fumble");
			add("Recovered");
			add("Fum Yards");
			add("Fum TDs");
			add("Blocked Kicks");
			add("Safetys");
			add("Points");
		}
	};
	// use initial spreadsheet as first game instance
	// TODO: may change later to use a game averages sheet.
    public SpreadsheetApi() {
    	// get first worksheet code and set it
    	this.worksheetcode = "nT64HfwYRYOJs5E2pKllpQ";
    	this.URI_STRING = "https://api.spreadsheet.com/v1/worksheets/"+ this.worksheetcode +"/rows";
    }
    
    // Creates a new spreadsheet and updates the worksheet code for the class
    public void setNewGame(String gameTitle) {
    	final String uri = "https://api.spreadsheet.com/v1/workbooks/dkUM7qnIR9SCWrelxIDGlg/worksheets";
    	JSONObject object = new JSONObject();
    	object.put("name", gameTitle);
    	sendPayload(object, uri, "POST");
    	ArrayList<String> worksheet_idList = parseIds(getPayload(uri));
    	this.worksheetcode = worksheet_idList.get(worksheet_idList.size()-1);
    	this.URI_STRING = "https://api.spreadsheet.com/v1/worksheets/"+ this.worksheetcode +"/rows";
	}
    
	// sets the worksheet code for the class to an existing spreadsheet.
    public void getGameSheet(int index) {
    	final String uri = "https://api.spreadsheet.com/v1/workbooks/dkUM7qnIR9SCWrelxIDGlg/worksheets";
    	ArrayList<String> worksheet_idList = parseIds(getPayload(uri));
    	this.worksheetcode = worksheet_idList.get(index-1);
    	this.URI_STRING = "https://api.spreadsheet.com/v1/worksheets/"+ this.worksheetcode +"/rows";
	}

    // posts an updated game to spreadsheets.com
    public int postGame(Game game) throws Exception {
    	if (sendPayload(populateSheet(game), URI_STRING, "POST") == 200) {
    		return 200;
    	} else {
    		throw new Exception("payload did not go through");
    	}
    	
    }
    
	// deletes the contents of current spreadsheet
    public int deleteSheet() throws Exception {
    	if (sendPayload(buildDeleteList(parseIds(getPayload(URI_STRING))), URI_STRING, "DELETE") == 200 || sendPayload(buildDeleteList(parseIds(getPayload(URI_STRING))), URI_STRING, "DELETE") == 400) {
    		return 200;
    	} else {
    		throw new Exception("payload did not go through");
    	}
	}

	// sends a jsonArray payload to spreadsheets.com
    public static int sendPayload(JSONArray payload, String uri, String postDelete) {
        try {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(postDelete);
            connection.setRequestProperty("Authorization", "Bearer " + KEY_STRING);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            OutputStream os = connection.getOutputStream();
            os.write(payload.toString().getBytes(StandardCharsets.UTF_8));
            os.flush();
            os.close();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return responseCode;
            } else {
                System.out.println("Failed. Response Code: " + responseCode);
                return responseCode;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
	// sends a jsonObject payload to spreadsheets.com
    public static int sendPayload(JSONObject payload, String uri, String postDelete) {
        try {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(postDelete);
            connection.setRequestProperty("Authorization", "Bearer " + KEY_STRING);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            OutputStream os = connection.getOutputStream();
            os.write(payload.toString().getBytes(StandardCharsets.UTF_8));
            os.flush();
            os.close();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return responseCode;
            } else {
                System.out.println("Failed. Response Code: " + responseCode);
                return responseCode;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    // grabs a payload from spreadsheets.com
    public static JsonNode getPayload(String uri) {

        try {
            URL url = new URL(uri + "?limit=300");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + KEY_STRING);
            connection.setDoOutput(true);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(response.toString());
                return jsonNode;
            } else {
                System.out.println("Failed. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;

    }

	// parses id codes from json node.
    public static ArrayList<String> parseIds(JsonNode node) {
        ArrayList<String> ids = new ArrayList<>();
        try {
        	JsonNode itemsNode = node.get("items");
            if (itemsNode != null && itemsNode.isArray()) {
                for (JsonNode itemNode : itemsNode) {
                    JsonNode idNode = itemNode.get("_id");
                    if (idNode != null && idNode.isTextual()) {
                        ids.add(idNode.asText());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return ids;
    }
    
	// builds a json array of ids to be deleted
    public static JSONArray buildDeleteList(ArrayList<String> ids) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (String id : ids) {
          JSONObject jsonObject = new JSONObject();
          jsonObject.put("_id", id);
          jsonArray.put(jsonObject);
        }
        return jsonArray;
      }

    // gets headers
    public static ArrayList<Object> getHeaders(String playType) throws Exception {
		switch (playType) {
	    case "Pass":
	        return PASSING_HEADER_STRINGS;
		case "Rush":
			return RUSHING_HEADER_STRINGS;
	    case "Receive":
	    	return RECEIVING_HEADER_STRINGS;
	    	
	    case "Kick":
	    	return KICK_HEADER_STRINGS;
	 
	    case "Punt":
	    	return PUNT_HEADER_STRINGS;
	  
	    case "FGPAT":
	    	return PATFG_HEADER_STRINGS;
	    
	    case "KOR":
	    	return KOR_HEADER_STRINGS;
	    
	    case "PuntRet":
	    	return POR_HEADER_STRINGS;
	    case "Defense":
	    	return DEFENSIVE_HEADER_STRINGS;
	    default:
			throw new Exception("Playtype did not match", new Throwable(playType));
	 
		}
	
	}
    
	// gets player stats list
    public static ArrayList<Object> getPlayerStatsList(String playType, Player player) throws Exception {
		switch (playType) {
	    case "Pass":
	        return ((PassingPlayer) player).toList();
		case "Rush":
			return ((RushingPlayer) player).toList();
	    case "Receive":
	    	return ((ReceivingPlayer) player).toList();
	    	
	    case "Kick":
	    	return ((KickingPlayer) player).toList();
	 
	    case "Punt":
	    	return ((PuntPlayer) player).toList();
	  
	    case "FGPAT":
	    	return ((PATFGPlayer) player).toList();
	    
	    case "KOR":
	    	return ((KORPlayer) player).toList();
	    
	    case "PuntRet":
	    	return ((PORPlayer) player).toList();
	    case "Defense":
	    	return ((DefensivePlayer) player).toList();
	    default:
			throw new Exception("Playtype did not match", new Throwable(playType));
	 
		}
	
	}
    
    // populates a sheet given a game
    public static JSONArray populateSheet(Game game) {
    	JSONArray array = new JSONArray();
		game.getPlayTypeMap().forEach((playType,playerList) -> {
			ArrayList<Object> list = new ArrayList<>();
			list.add(playType);
			array.put(buildRow(buildCells(list)));
			
			// if playtype == pass then array.put(buildRow(buildCells(PASSING_HEADER_STRINGS)));
			try {
				array.put(buildRow(buildCells(getHeaders(playType))));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			for (Player player : playerList) {
				try {
					array.put(buildRow(buildCells(getPlayerStatsList(playType, player))));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
		return array;
	}
    
    
    // builds a row of the sheet
    public static JSONObject buildRow(JSONArray array) {
	    // Create a row object
	    JSONObject row = new JSONObject();
	    row.put("cellData", array);
	    return row;
		
	}
    
	// builds an individual cell/s of the sheet
    public static JSONArray buildCells(ArrayList<Object> list) {
        JSONArray cellDataArray = new JSONArray();
        char columnId = 'A'; // Start with 'A' as the initial value of columnId1

        for (Object stat : list) {
            // build one cell of the row
            JSONObject cellData = new JSONObject();
            cellData.put("field", Character.toString(columnId)); // Update the field with the current value of columnId1
            cellData.put("data", stat);
            cellDataArray.put(cellData);
            
            columnId++; // Increment the columnId1 character for the next iteration
        }

        return cellDataArray;
    }
    
}