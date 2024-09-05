package de.exxcellent.challenge.DataLoader;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvDataLoaderTest {

    @Test
    void loadDataFromPath() {
        CsvDataLoader csvDataLoader = new CsvDataLoader();

        List<List<String>> data = csvDataLoader.loadDataFromPath("src/test/resources/football.csv");
        assertEquals(data.get(0).get(5), "Goals");
        assertEquals(data.get(7).get(0), "West_Ham");
        assertEquals(data.get(10).get(5), "55");

        Exception e = assertThrows(RuntimeException.class, () -> csvDataLoader.loadDataFromPath("fictional_path"));
        assertEquals(e.getMessage(), "Data to be loaded could not be located.");
    }
}