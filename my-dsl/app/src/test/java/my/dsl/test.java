package my.dsl;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import my.dsl.API.SpreadsheetApi;
import my.dsl.Game.Game;
import my.dsl.Parser.Parser;
import my.dsl.Parser.StringGrouping;

public class test {
    @Test
    public void test1() throws Exception {
        Parser parse = new Parser();
        System.out.println(parse.toInt(parse.parseNumber("-3")) < 0);
    }
    @Test
    public void buildWholeSpreadsheet() throws Exception {
        Stream<String> lines = Files.lines(Paths.get("/workspaces/Bbecker8-Artifact/my-dsl/app/src/test/java/my/dsl/testFile"));
        StringGrouping group = new StringGrouping();
        SpreadsheetApi api = new SpreadsheetApi();

        Game game = new Game();
        if (api.deleteSheet() == 200) {
            lines.forEach(line -> {
                try {
                    group.update(line, game);
                    if (api.deleteSheet() == 200 || api.deleteSheet() == 400) {
                        if (api.postGame(game) == 200) {
                            System.out.println("success");
                        } else {
                            throw new Exception("Game could not Be Posted");
                        }
                    } else {
                        throw new Exception("Sheet Could Not Be Deleted");
                    }
                } catch (Exception e){
                    e.getStackTrace();
                }
            });
            
        }
        lines.close();
        
    }
}
