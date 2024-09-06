package de.exxcellent.challenge.Excepetions;

public class WrongFileFormatException extends RuntimeException {
    public WrongFileFormatException(String s) {
        super(s);
    }
}
