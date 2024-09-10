package de.exxcellent.challenge.ChallengeSolver;

import de.exxcellent.challenge.DataObjects.ChallengeDataStructure;
import de.exxcellent.challenge.DataLoader.ChallengeDataLoader;
import de.exxcellent.challenge.Exceptions.ListHasWrongSizeException;
import de.exxcellent.challenge.Exceptions.WrongFormatForTableException;
import de.exxcellent.challenge.Exceptions.WrongNameException;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * This class can solve the given challenge.
 */
public class ChallengeSolver {

    /**
     * No instance of this class is necessary.
     */
    private ChallengeSolver(){}

    /**
     * This method solves the given challenge. It reads the data with the dataLoader and the path. Then it obtains the
     * values from the columns with name column1Name and column2Name and calculates the absolute differences between their entries.
     * Lastly, it returns the index where the minimum difference is attained.
     * @param dataLoader A ChallengeDataLoader that can read the data specified in path.
     * @param path The path to the data.
     * @param column1Name The name of the first column that should be used in the calculation of the absolute difference.
     * @param column2Name The name of the second column that should be used in the calculation of the absolute difference.
     * @return The index where the minimum absolute difference between the entries of the two columns is attained.
     */
    public static List<String> solveChallenge(ChallengeDataLoader dataLoader, String path, String column1Name, String column2Name) {
        try {
            ChallengeDataStructure data = dataLoader.loadDataFromPathInChallengeStructure(path);
            List<Double> firstColumn = data.getValueColumnByName(column1Name);
            List<Double> secondColumn = data.getValueColumnByName(column2Name);

            List<Integer> positionsOfMinDifference = findPositionOfMinDifferenceBetweenLists(firstColumn, secondColumn);
            List<String> indicesWithMinimumDifferenceValue = positionsOfMinDifference.stream().map(position -> data.getIndices().get(position)).toList();

            return indicesWithMinimumDifferenceValue;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (WrongNameException e) {
            throw new RuntimeException("At least one column name could not be found in the data.");
        } catch (WrongFormatForTableException | ListHasWrongSizeException e) {
            throw new RuntimeException("The data might have gotten corrupted. Check the file for correct number of rows and columns.");
        }
    }

    /**
     * This returns the position of the minimum absolute difference between the entries of two lists.
     * @param list1 First list
     * @param list2 Second list
     * @return the position of the minimum absolute difference between the entries of two lists.
     * @throws ListHasWrongSizeException The lists should have the same size, otherwise an exception is thrown.
     */
    private static List<Integer> findPositionOfMinDifferenceBetweenLists(List<Double> list1, List<Double> list2) throws ListHasWrongSizeException {
        if(list1.size() != list2.size()){
            throw new ListHasWrongSizeException("The two lists are of different size.");
        }
        List<Double> listOfDifferences = IntStream.range(0, list1.size()).mapToDouble(i -> Math.abs(list1.get(i) - list2.get(i))).boxed().toList();
        Double minValue = Collections.min(listOfDifferences);
        List<Integer> indicesOfMinimumValue = IntStream.range(0, list1.size()).filter(i -> listOfDifferences.get(i).equals(minValue)).boxed().toList();

        return indicesOfMinimumValue;
    }
}
