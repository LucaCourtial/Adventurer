package org.adventurer.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MapWorld extends GameEntity {
    private String name;
    private int height;
    private int width;
    private Path pathMap;
    private String[][] mapArray;

    public void init() {
        try (Stream<String> lines = Files.lines(this.pathMap)) {
            List<String> list = lines.collect(Collectors.toList());
            this.height = list.size();
            this.width = list.get(0).split("").length;
            this.mapArray = loadMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Display the informations about the map
     */
    public void displayInformations() {
        System.out.println("Map Informations :");
        System.out.println("\tname : " + this.name);
        System.out.println("\tsize : " + this.width + " x " + this.height);
    }

    /**
     * Fill a 2D String Array with the content of a file
     * @return 2D String Array which is a representation of the map in the file
     */
    private String[][] loadMap() throws Exception {
        String[][] mapArray = new String[this.width][this.height];
        try (Stream<String> lines = Files.lines(this.pathMap)) {
            List<String> list = lines.collect(Collectors.toList());
            // Columns (height)
            IntStream.range(0, height).forEach(y -> {
                // Rows (width)
                IntStream.range(0, width).forEach( x -> mapArray[x][y] = list.get(x).split("")[y]);
            });
            return mapArray;
        } catch (Exception e) {
            throw new Exception("Error during map loading.");
        }
    }
}
