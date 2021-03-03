package org.adventurer;

import org.adventurer.data.Character;
import org.adventurer.data.*;
import org.adventurer.exception.HitTreeException;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AdventurerApp {

    public static void main( String[] args ) throws URISyntaxException, HitTreeException {

        // Set up all objects contained in the Game
        String fileNameRun = "runs/run";
        String fileNameMap = "maps/carte.txt";
        String characterName = "Arnold Schwarzenegger";
        Path pathRun;
        Path pathMap;

        Coordinates coordinates = new Coordinates();
        Course course = new Course(fileNameRun);
        Game game = new Game();
        MapWorld mapWorld = new MapWorld();
        mapWorld.setName(fileNameMap);

        // Check if the file exists or not with a try/catch
        try {
            pathRun = Paths.get(ClassLoader.getSystemResource(course.getName()).toURI());
            pathMap = Paths.get(ClassLoader.getSystemResource(mapWorld.getName()).toURI());
        } catch (NullPointerException e) {
            throw new NullPointerException("File not found.");
        }

        mapWorld.setPathMap(pathMap);
        Character character = new Character(characterName, course, coordinates, pathRun);

        game.setCharacter(character);
        game.setMapWorld(mapWorld);

        // When all the objects are passed in parameter we can start to run the game
        game.run();
    }
}
