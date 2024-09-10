package de.exxcellent.challenge.Exceptions;

/**
 * This exception is thrown if a method is called with an incorrect name parameter.
 */
public class WrongNameException extends Exception{
    public WrongNameException(String s){
        super(s);
    }
}
