package org.adventurer.exception;

public class HitTreeException extends Exception {
    public HitTreeException(String name) {
        super(name + " fall on a tree too hard! He's dead!");
    }
}
