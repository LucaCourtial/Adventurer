package org.adventurer.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.adventurer.exception.HitTreeException;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
/*
  Class composed with GameEntities useful to run the game Adventurer.
 */
public class Game {

    private Character character;
    private MapWorld mapWorld;

    /**
     * Run the game
     * @throws HitTreeException if the character spawn on a tree
     */
    public void run() throws HitTreeException {
        // Initialize objects with resources files
        this.mapWorld.init();
        this.character.init();

        // Display informations about the character and the map where he will walk
        this.mapWorld.displayInformations();
        this.character.displayInformations();
        this.character.walk(mapWorld.getMapArray());
    }

}
