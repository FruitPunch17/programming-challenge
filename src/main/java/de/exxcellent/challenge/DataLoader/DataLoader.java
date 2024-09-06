package de.exxcellent.challenge.DataLoader;

import de.exxcellent.challenge.Excepetions.WrongFormatForTableException;

import java.util.List;

/**
 * A class that implements this interface can read data from a source.
 */
public interface DataLoader {
    /**
     * Obtains data from a path (for example a file or a database) and converts it to a tabular structure.
     * @param path Path where data can be obtained from.
     * @return The data in a tabular structure.
     * @throws WrongFormatForTableException If the data does not yield a proper tabular structure, an exception is thrown.
     */
    List<List<String>> loadDataFromPathAsTable(String path) throws WrongFormatForTableException;
}
