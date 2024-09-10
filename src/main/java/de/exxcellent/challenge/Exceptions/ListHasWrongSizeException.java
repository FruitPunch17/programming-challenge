package de.exxcellent.challenge.Exceptions;

/**
 * This exception is thrown when a list has the wrong size.
 */
public class ListHasWrongSizeException extends Exception{
    public ListHasWrongSizeException(String s){
        super(s);
    }
}
