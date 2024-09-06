package de.exxcellent.challenge.DataLoader;

import de.exxcellent.challenge.Excepetions.WrongFileFormatException;
import de.exxcellent.challenge.Excepetions.WrongFormatForTableException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class can read csv files and return tabular structures to be processed.
 */
public class CsvDataLoader implements DataLoader {
    
    String delimiter = ",";

    /**
     * By default, the csv delimiter is ",". If another one is necessary, set it with this method.
     * @param delimiter The delimiter to be set.
     */
    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * This method reads a csv file and converts it to a tabular structure. If the file does not end with ".csv"
     * or does not have a proper tabular structure an exception is thrown.
     * @param path The path where the file to be read lies.
     * @return A list of lists of strings that emulate the tabular structure of the data.
     * @throws WrongFileFormatException If the file has the wrong format, an exception is thrown.
     */
    @Override
    public List<List<String>> loadDataFromPathAsTable(String path) throws WrongFormatForTableException {

        // throw an exception if the file path does not end with ".csv"
        if(!path.endsWith(".csv")) throw new WrongFileFormatException("The file path given does not lead to a CSV file.");

        // read each line of data in path and split it on the delimiter and put the split parts into a list
        List<List<String>> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(delimiter);
                data.add(Arrays.asList(values));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Data to be loaded could not be located.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // throw an exception if the data does not yield a "rectangular" table,
        // that is, the number of its columns is not consistent across all lines
        data.forEach(line -> {if(line.size() != data.get(0).size()) throw new WrongFormatForTableException(
                "The number of columns in the file that was read are not consistent across all its lines.");});

        return data;
    }
}
