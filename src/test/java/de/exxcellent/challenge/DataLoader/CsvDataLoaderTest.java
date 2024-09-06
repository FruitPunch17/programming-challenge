package de.exxcellent.challenge.DataLoader;

import de.exxcellent.challenge.Excepetions.WrongFileFormatException;
import de.exxcellent.challenge.Excepetions.WrongFormatForTableException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvDataLoaderTest {

    @Test
    void loadDataFromPath() {
        CsvDataLoader csvDataLoader = new CsvDataLoader();

        List<List<String>> data = csvDataLoader.loadDataFromPathAsTable("src/test/resources/football.csv");
        assertEquals(data.get(0).get(5), "Goals");
        assertEquals(data.get(7).get(0), "West_Ham");
        assertEquals(data.get(10).get(5), "55");

        Exception dataCouldNotBeFoundException = assertThrows(RuntimeException.class,
                () -> csvDataLoader.loadDataFromPathAsTable("fictional_path.csv"));
        assertEquals(dataCouldNotBeFoundException.getMessage(), "Data to be loaded could not be located.");

        Exception wrongFileFormatException = assertThrows(WrongFileFormatException.class,
                () -> csvDataLoader.loadDataFromPathAsTable("src/test/resources/test.txt"));
        assertEquals(wrongFileFormatException.getMessage(), "The file path given does not lead to a CSV file.");

        Exception wrongFormatForTableException = assertThrows(WrongFormatForTableException.class,
                () -> csvDataLoader.loadDataFromPathAsTable("src/test/resources/weather_modified.csv"));
        assertEquals(wrongFormatForTableException.getMessage(),
                "The number of columns in the file that was read are not consistent across all its lines.");
    }
}