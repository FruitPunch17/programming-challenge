package de.exxcellent.challenge.Excepetions;

/*
This exception is thrown when the contents of a file cannot be converted to a correct table.
 */
public class WrongFormatForTableException extends RuntimeException {
    public WrongFormatForTableException(String s) {
        super(s);
    }
}
