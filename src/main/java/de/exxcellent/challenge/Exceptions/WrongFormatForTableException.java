package de.exxcellent.challenge.Exceptions;

/**
 * This exception is thrown when data cannot be converted to a correct table (irregular number of columns for example).
 */
public class WrongFormatForTableException extends Exception {
    public WrongFormatForTableException(String s) {
        super(s);
    }
}
