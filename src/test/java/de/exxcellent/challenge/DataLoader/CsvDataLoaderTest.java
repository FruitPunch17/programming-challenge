package de.exxcellent.challenge.DataLoader;

import de.exxcellent.challenge.DataObjects.ChallengeDataStructure;
import de.exxcellent.challenge.Exceptions.WrongFileFormatException;
import de.exxcellent.challenge.Exceptions.WrongFormatForTableException;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvDataLoaderTest {

    @Test
    void loadDataFromPath() {
        CsvDataLoader csvDataLoader = new CsvDataLoader();

        try {
            List<List<String>> weatherData = csvDataLoader.loadDataFromPath("src/test/resources/weather.csv");
            assertEquals(weatherData.get(0).get(1), "MxT");
            assertEquals(weatherData.get(3).get(6), "350");

            List<List<String>> footballData = csvDataLoader.loadDataFromPath("src/test/resources/football.csv");
            assertEquals(footballData.get(6).get(0), "Chelsea");
            assertEquals(footballData.get(3).get(6), "45");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void loadDataFromPathExceptionChecks() {
        CsvDataLoader csvDataLoader = new CsvDataLoader();

        Exception dataCouldNotBeFoundException = assertThrows(FileNotFoundException.class,
                () -> csvDataLoader.loadDataFromPath("fictional_path.csv"));
        assertEquals(dataCouldNotBeFoundException.getMessage(), "fictional_path.csv (Das System kann die angegebene Datei nicht finden)");

        Exception wrongFileFormatException = assertThrows(WrongFileFormatException.class,
                () -> csvDataLoader.loadDataFromPath("src/test/resources/test.txt"));
        assertEquals(wrongFileFormatException.getMessage(), "The file path given does not lead to a CSV file.");
    }

    @Test
    void loadDataFromPathDelimiterSetter() {
        CsvDataLoader csvDataLoader = new CsvDataLoader();

        try {
            assertEquals(csvDataLoader.loadDataFromPath("src/test/resources/file_with_non_standard_delimiter.csv").get(0).get(0),
                    "column1; column2; column3");

            csvDataLoader.setDelimiter(";");

            assertEquals(csvDataLoader.loadDataFromPath("src/test/resources/file_with_non_standard_delimiter.csv").get(0).get(0),
                    "column1");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void loadDataFromPathInChallengeStructure(){
        CsvDataLoader csvDataLoader = new CsvDataLoader();

        try {
            ChallengeDataStructure weatherChallengeData = csvDataLoader.loadDataFromPathInChallengeStructure("src/test/resources/weather.csv");
            assertEquals(weatherChallengeData.getIndexHeader(), "Day");
            assertEquals(weatherChallengeData.getValueHeader().get(7), "Dir");
            assertEquals(weatherChallengeData.getIndices().get(3), "4");
            assertEquals(weatherChallengeData.getValues().get(6).get(3), 53.0);

            ChallengeDataStructure footballChallengeData = csvDataLoader.loadDataFromPathInChallengeStructure("src/test/resources/football.csv");
            assertEquals(footballChallengeData.getIndexHeader(), "Team");
            assertEquals(footballChallengeData.getValueHeader().get(4), "Goals");
            assertEquals(footballChallengeData.getIndices().get(6), "West_Ham");
            assertEquals(footballChallengeData.getValues().get(9).get(5), 51.0);
        } catch (IOException | WrongFormatForTableException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void loadDataFromPathInChallengeStructureExceptionChecks(){
        CsvDataLoader csvDataLoader = new CsvDataLoader();

        Exception wrongFormatForTableException = assertThrows(WrongFormatForTableException.class,
                () -> csvDataLoader.loadDataFromPathInChallengeStructure("src/test/resources/weather_missing_a_value.csv"));
        assertEquals(wrongFormatForTableException.getMessage(),
                "The number of columns in the file that was read are not consistent across all its lines.");
    }
}