package org.adventurer.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.adventurer.constant.Direction;
import org.adventurer.exception.HitTreeException;
import org.adventurer.exception.OutOfMapException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Character extends GameEntity {

    private String name;
    private Course course;
    private Coordinates coordinates;
    private Path pathRun;

    /**
     * Initialize course and coordinates with files placed in the resources directory
     */
    public void init() {
            List<String> list;
            try (Stream<String> lines = Files.lines(this.pathRun)) {
                list = lines.limit(2).collect(Collectors.toList());
                this.coordinates.setX(Integer.parseInt(list.get(0).split(",")[0]));
                this.coordinates.setY(Integer.parseInt(list.get(0).split(",")[1]));
                this.course.setSteps(Arrays.asList(list.get(1).split("")));
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    /**
     * Display the informations about the character
     */
    public void displayInformations() {
        System.out.println("\nHere's the story :");
        System.out.println("\tYour adventurer is " + name + " and he's ready to fight in a dangerous forest !");
        System.out.println("\tHe's gonna land on (" + this.coordinates.getX() + "," + this.coordinates.getY() + ")");
        System.out.println("\tThen he will try to walk in those directions = " + String.join(",", this.course.getSteps()));
        System.out.println("\tReady? Go!\n");
    }

    /**
     * Move the character in the map
     * @param mapArray is the representation of the map file into a double array
     */
    public void walk(String[][] mapArray) throws HitTreeException {
        System.out.println("\nPlane incoming... \n" + this.name + " is landing on the map on (" + this.coordinates.getX() + ", " + this.coordinates.getY() + ")!");

        // Check if his spawn is out of the map
        if(mapArray.length < this.coordinates.getY() || mapArray[0].length < this.coordinates.getX() || this.coordinates.getY() < 0 || this.coordinates.getX() < 0) {
            throw new OutOfMapException(this.name);
        }

        // Check if his spawn is on a tree
        if(mapArray[this.coordinates.getY()][this.coordinates.getX()].equals("#")) {
            throw new HitTreeException(this.getName());
        }

        AtomicInteger newY = new AtomicInteger();
        AtomicInteger newX = new AtomicInteger();

        // We move our character in function of his steps. We only move if our character meets a space and if he stay on the map
        this.getCourse().getSteps().forEach(s -> {
            // UpperCase is set to handle lower case in steps
            switch (s.toUpperCase()) {
                case Direction.N:
                    newY.set(this.coordinates.getY() - 1);
                    if (0 < newY.get() && mapArray[newY.get()][this.coordinates.getX()].equals(" ")) {
                        this.coordinates.setY(newY.get());
                    }
                    break;
                case Direction.S:
                    newY.set(this.coordinates.getY() + 1);
                    if (mapArray.length >= newY.get() && mapArray[newY.get()][this.coordinates.getX()].equals(" ")) {
                        this.coordinates.setY(newY.get());
                    }
                    break;
                case Direction.O:
                    newX.set(this.coordinates.getX() - 1);
                    if (0 < newX.get() && mapArray[this.coordinates.getY()][newX.get()].equals(" ")) {
                        this.coordinates.setX(newX.get());
                    }
                    break;
                case Direction.E:
                    newX.set(this.coordinates.getX() + 1);
                    if (mapArray[0].length >= newX.get() && mapArray[this.coordinates.getY()][newX.get()].equals(" ")) {
                        this.coordinates.setX(newX.get());
                    }
                    break;
                default:
                    System.out.println("There is an error in the directions you gave to " + this.name + "...");
            }
        });
        displayPosition();
    }

    /**
     * Display character's position on the map
     */
    private void displayPosition() {
        System.out.println("After many fights " + this.name + " stopped his walk in (" + this.coordinates.getX() + ", " + coordinates.getY() + "). He's waiting his helicopter, ready to leave this place.");
    }

}
