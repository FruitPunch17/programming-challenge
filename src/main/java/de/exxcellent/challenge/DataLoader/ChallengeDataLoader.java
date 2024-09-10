package de.exxcellent.challenge.DataLoader;

import de.exxcellent.challenge.DataObjects.ChallengeDataStructure;
import de.exxcellent.challenge.Exceptions.WrongFormatForTableException;

import java.io.IOException;

/**
 * A class that implements this interface can read data from a source and create ChallengeDataStructures from it.
 */
public interface ChallengeDataLoader {
    /**
     * Obtains data from a path (for example a file or a database) and converts it to an instance of ChallengeDataStructure.
     * @param path Path where data can be obtained from.
     * @return The data in form of an instance of ChallengeDataStructure.
     * @throws WrongFormatForTableException If the data does not yield a proper tabular structure, an exception is thrown.
     */
    ChallengeDataStructure loadDataFromPathInChallengeStructure(String path) throws WrongFormatForTableException, IOException;
}
