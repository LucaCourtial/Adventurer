package org.adventurer;

import org.adventurer.data.Character;
import org.adventurer.data.*;
import org.adventurer.exception.HitTreeException;
import org.adventurer.exception.OutOfMapException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Unit test for simple App.
 */
public class AdventurerAppTest {

    private static Course course;
    private static Coordinates coordinates;
    private static Character character;
    private static MapWorld mapWorld;

    @BeforeClass
    public static void initClass() {
        System.out.println("________ AdventurerAppTest ___________");
        coordinates = new Coordinates();
        course = new Course();
        character = new Character("Toto", course, coordinates, null);
        mapWorld = new MapWorld();
    }

    @Before
    public void initTest() {
        System.out.println("\n");
    }

    /**
     * Test game.loadMap with file runClassic
     */
    @Test
    public void testClassicRun() throws URISyntaxException, HitTreeException {
        System.out.println("________ testClassicRun ___________");

        course.setName("runs/runClassic");
        Path pathRun = Paths.get(ClassLoader.getSystemResource(course.getName()).toURI());

        character.setPathRun(pathRun);
        character.init();


        mapWorld.setName("maps/map.txt");
        Path pathMap = Paths.get(ClassLoader.getSystemResource(mapWorld.getName()).toURI());
        mapWorld.setPathMap(pathMap);
        mapWorld.init();

        System.out.println("________ Start in (" + coordinates.getX() + ", " + coordinates.getY() + ") ___________");

        character.walk(mapWorld.getMapArray());

        Assert.assertEquals(9, character.getCoordinates().getX());
        Assert.assertEquals(2, character.getCoordinates().getY());
    }

    /**
     * Test game.loadMap with file runWithTreeOnTheWay
     */
    @Test
    public void testRunWithTreeOnTheWay() throws URISyntaxException, HitTreeException {
        System.out.println("________ testRunWithTreeOnTheWay ___________");

        course.setName("runs/runWithTreeOnTheWay");
        Path pathRun = Paths.get(ClassLoader.getSystemResource(course.getName()).toURI());

        character.setPathRun(pathRun);
        character.init();


        mapWorld.setName("maps/map.txt");
        Path pathMap = Paths.get(ClassLoader.getSystemResource(mapWorld.getName()).toURI());
        mapWorld.setPathMap(pathMap);
        mapWorld.init();

        System.out.println("________ Start in (" + coordinates.getX() + ", " + coordinates.getY() + ") ___________");

        character.walk(mapWorld.getMapArray());
        Assert.assertEquals(5, character.getCoordinates().getX());
        Assert.assertEquals(7, character.getCoordinates().getY());
    }

    /**
     * Test game.loadMap with file runWithLandingOutOfTheMap
     */
    @Test(expected = OutOfMapException.class)
    public void testWithLandingOutOfTheMap() throws URISyntaxException, HitTreeException {
        System.out.println("________ testWithLandingOutOfTheMap ___________");

        course.setName("runs/runWithLandingOutOfTheMap");
        Path pathRun = Paths.get(ClassLoader.getSystemResource(course.getName()).toURI());

        character.setPathRun(pathRun);
        character.init();


        mapWorld.setName("maps/map.txt");
        Path pathMap = Paths.get(ClassLoader.getSystemResource(mapWorld.getName()).toURI());
        mapWorld.setPathMap(pathMap);
        mapWorld.init();

        System.out.println("________ Start in (" + coordinates.getX() + ", " + coordinates.getY() + ") ___________");

        character.walk(mapWorld.getMapArray());

    }

    /**
     * Test game.loadMap with file runWithLandingOutOfTheMap
     */
    @Test(expected = HitTreeException.class)
    public void testWithLandingOnTree() throws URISyntaxException, HitTreeException {
        System.out.println("________ testWithLandingOnTree ___________");

        course.setName("runs/runWithLandingOnTree");
        Path pathRun = Paths.get(ClassLoader.getSystemResource(course.getName()).toURI());

        character.setPathRun(pathRun);
        character.init();


        mapWorld.setName("maps/map.txt");
        Path pathMap = Paths.get(ClassLoader.getSystemResource(mapWorld.getName()).toURI());
        mapWorld.setPathMap(pathMap);
        mapWorld.init();

        System.out.println("________ Start in (" + coordinates.getX() + ", " + coordinates.getY() + ") ___________");

        character.walk(mapWorld.getMapArray());

    }

    /**
     * Test game.loadMap with file runOverTheMapEdge
     */
    @Test
    public void testWithRunOverTheMapEdge() throws URISyntaxException, HitTreeException {
        System.out.println("________ testWithRunOverTheMapEdge ___________");

        course.setName("runs/runOverTheMapEdge");
        Path pathRun = Paths.get(ClassLoader.getSystemResource(course.getName()).toURI());

        character.setPathRun(pathRun);
        character.init();


        mapWorld.setName("maps/map.txt");
        Path pathMap = Paths.get(ClassLoader.getSystemResource(mapWorld.getName()).toURI());
        mapWorld.setPathMap(pathMap);
        mapWorld.init();

        System.out.println("________ Start in (" + coordinates.getX() + ", " + coordinates.getY() + ") ___________");

        character.walk(mapWorld.getMapArray());
        Assert.assertEquals(3, character.getCoordinates().getX());
        Assert.assertEquals(0, character.getCoordinates().getY());
    }
}
