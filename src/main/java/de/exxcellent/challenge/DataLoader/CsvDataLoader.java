package de.exxcellent.challenge.DataLoader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvDataLoader implements DataLoader {
    @Override
    public List<List<String>> loadDataFromPath(String path) {
        List<List<String>> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                data.add(Arrays.asList(values));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Data to be loaded could not be located.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}
