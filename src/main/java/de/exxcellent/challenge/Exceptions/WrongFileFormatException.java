package de.exxcellent.challenge.Exceptions;

import java.io.IOException;

/**
 * This exception is thrown when a file has the wrong format.
 */
public class WrongFileFormatException extends IOException {
    public WrongFileFormatException(String s) {
        super(s);
    }
}
