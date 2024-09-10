package de.exxcellent.challenge.DataLoader;

import de.exxcellent.challenge.DataObjects.ChallengeDataStructure;
import de.exxcellent.challenge.Exceptions.WrongFileFormatException;
import de.exxcellent.challenge.Exceptions.WrongFormatForTableException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class can read csv files.
 */
public class CsvDataLoader implements ChallengeDataLoader {
    private String delimiter = ",";

    /**
     * By default, the csv delimiter is ",". If another one is necessary, set it with this method.
     * @param newDelimiter The delimiter to be set.
     */
    public void setDelimiter(String newDelimiter) {
        delimiter = newDelimiter;
    }


    /**
     *
     * @param path The path where the file to be read lies.
     * @return A list of lists of strings that represents the data.
     * @throws WrongFileFormatException If the file has the wrong format, an exception is thrown.
     */
    public List<List<String>> loadDataFromPath(String path) throws IOException {
        // throw an exception if the file path does not end with ".csv"
        if(!path.endsWith(".csv")) throw new WrongFileFormatException("The file path given does not lead to a CSV file.");

        // tracks if there is a line in the file with no delimiter, in that case the delimiter might be set incorrectly
        boolean noDelimiterFound = false;
        // read each line of data in path and split it on the delimiter and put the split parts into a list
        List<List<String>> data = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        while ((line = br.readLine()) != null) {
            if(!line.contains(delimiter)){
                noDelimiterFound = true;
            }
            String[] values = line.split(delimiter);
            data.add(Arrays.asList(values));
        }

        if(noDelimiterFound){
            System.err.println("There is a line in the CSV file that does not contain the current delimiter. " +
                    "You might want to check if the delimiter is set correctly.");
        }

        return data;
    }


    /**
     * This method reads a csv file and converts it to an instance of ChallengeDataStructure. If the path does not end with ".csv"
     * an exception is thrown. Also, the usual input-output-exceptions may be thrown, that is, FileNotFoundExceptions and IOExceptions.
     * The data is expected to be of the following form: The top row is the header and the leftmost column are the indices of the table.
     * @param path The path where the file to be read lies.
     * @return A ChallengeDataStructure that emulates the tabular structure of the data.
     * @throws WrongFormatForTableException An exception is thrown if the file does not yield proper tabular structure.
     */
    @Override
    public ChallengeDataStructure loadDataFromPathInChallengeStructure(String path) throws WrongFormatForTableException, IOException {
        // load the data from the given path
        List<List<String>> data = loadDataFromPath(path);

        // throw an exception if the data does not yield a "rectangular" table,
        // that is, the number of columns is not consistent across all lines
        for(List<String> line : data){
            if(line.size() != data.get(0).size()){
                throw new WrongFormatForTableException(
                        "The number of columns in the file that was read are not consistent across all its lines.");
            }
        }

        return new ChallengeDataStructure(
                // the topmost and leftmost entry is the index header
                data.get(0).get(0),
                // the leftmost column (without the index header) are the indices
                data.stream().skip(1).map(row -> row.get(0)).toList(),
                // the topmost row (without the index header) are the value headers
                data.get(0).stream().skip(1).toList(),
                // the values are obtained by skipping the first row and the first column
                data.stream().skip(1).map(row -> row.stream().skip(1).toList()).toList());
    }
}
