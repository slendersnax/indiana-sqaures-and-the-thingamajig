// Orban Abel, 621
package collection.entities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

public class Level {
    public ArrayList<Square> getLevelEntities(int levelNumber, int standardWidth, int levelWidth, int levelHeight, Square player) throws IOException {
        ArrayList<Square> entities = new ArrayList<>();
        // every level is actually a text file, every character corresponds to a tile/square type
        BufferedReader reader = new BufferedReader(new FileReader("data/levels/level_" + levelNumber + ".txt"));

        StringBuilder str = new StringBuilder();
        String res;
        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            str.append(currentLine);
        }

        res = str.toString();

        // creating entities based on the level's data
        int k = 0;

        while(k < res.length()) {
            int i = k / levelWidth;
            int j = k % levelWidth;

            switch (res.charAt(k)) {
                case '0':
                    entities.add(new Square(standardWidth, j * standardWidth, i * standardWidth, new File("data/sprites/bw_brickwall.png")));
                    break;
                case '1':
                    player.x = j * standardWidth;
                    player.y = i * standardWidth;
                    break;
                case '2':
                    entities.add(new FinishSquare(standardWidth, j * standardWidth, i * standardWidth, new File("data/sprites/door.png") ));
                    break;
                case '3':
                    entities.add(new Thingamajig(standardWidth, j * standardWidth, i * standardWidth, new File("data/sprites/thingamajig.png") ));
                    break;
                case '4':
                    entities.add(new StairSquare(standardWidth, j * standardWidth, i * standardWidth, new File("data/sprites/stairs.png") ));
                    break;
                case '5':
                    entities.add(new GateSquare(standardWidth, j * standardWidth, i * standardWidth, new File("data/sprites/closed_gate.png") ));
                    break;
                default:
                    break;
            }

            k++;
        }

        return entities;
    }
}
