package de.exxcellent.challenge.DataObjects;

import de.exxcellent.challenge.Exceptions.WrongNameException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChallengeDataStructureTest {

    @Test
    void getValueColumnByName() {
        String indexHeader = "IndexHeader";
        List<String> indices = List.of("index1", "index2", "index3");
        List<String> valueHeaders = List.of("header1", "header2");
        List<List<String>> values = List.of(
                List.of("1.0", "2.0"),
                List.of("3.0", "4.0"),
                List.of("5.0", "6.0")
        );

        ChallengeDataStructure data = new ChallengeDataStructure(indexHeader, indices, valueHeaders, values);

        try {
            List<Double> column1 = data.getValueColumnByName("header1");
            assertEquals(column1, List.of(1.0, 3.0, 5.0));
        } catch (WrongNameException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getValueColumnByNameExceptionChecks() {
        String indexHeader = "IndexHeader";
        List<String> indices = List.of("index1", "index2", "index3");
        List<String> valueHeaders = List.of("header1", "header2");
        List<List<String>> values = List.of(
                List.of("1.0", "2.0"),
                List.of("3.0", "4.0"),
                List.of("5.0", "6.0")
        );

        ChallengeDataStructure data = new ChallengeDataStructure(indexHeader, indices, valueHeaders, values);

        Exception e = assertThrows(WrongNameException.class, () -> data.getValueColumnByName("wrongName"));
        assertEquals(e.getMessage(), "The name of the column could not be found.");

    }
}