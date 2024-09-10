package de.exxcellent.challenge.ChallengeSolver;

import de.exxcellent.challenge.DataLoader.ChallengeDataLoader;
import de.exxcellent.challenge.DataObjects.ChallengeDataStructure;
import de.exxcellent.challenge.Exceptions.WrongFormatForTableException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ChallengeSolverTest {

    @Mock
    ChallengeDataLoader challengeDataLoader;

    @Test
    void solveChallenge() throws WrongFormatForTableException, IOException {
        MockitoAnnotations.initMocks(this);

        String indexHeader = "IndexHeader";
        List<String> indices = List.of("index1", "index2", "index3");
        List<String> valueHeaders = List.of("header1", "header2");
        List<List<String>> values = List.of(
                List.of("-1.0", "2.0"),
                List.of("3.0", "-4.0"),
                List.of("5.0", "6.0")
        );

        ChallengeDataStructure data = new ChallengeDataStructure(indexHeader, indices, valueHeaders, values);
        when(challengeDataLoader.loadDataFromPathInChallengeStructure("test_path")).thenReturn(data);

        String path = "test_path";
        String column1Name = "header1";
        String column2Name = "header2";
        List<String> minimumIndices = ChallengeSolver.solveChallenge(challengeDataLoader, path, column1Name, column2Name);
        assertEquals(minimumIndices, List.of("index3"));
    }
}