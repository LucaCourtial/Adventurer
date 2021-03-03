package org.adventurer.exception;

public class OutOfMapException extends IndexOutOfBoundsException {
    public OutOfMapException(String name) {
        super(name + " is out of the map ! He will fall in space !");
    }
}
