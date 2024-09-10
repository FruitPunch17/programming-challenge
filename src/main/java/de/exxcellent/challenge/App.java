package de.exxcellent.challenge;

import de.exxcellent.challenge.ChallengeSolver.ChallengeSolver;
import de.exxcellent.challenge.DataLoader.CsvDataLoader;

import java.util.List;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {

        CsvDataLoader csvDataLoader = new CsvDataLoader();
        List<String> daysWithSmallestTemperatureSpread = ChallengeSolver.solveChallenge(csvDataLoader,
                "src/main/resources/de/exxcellent/challenge/weather.csv", "MxT", "MnT");

        List<String> teamsWithSmallestGoalDifference = ChallengeSolver.solveChallenge(csvDataLoader,
                "src/main/resources/de/exxcellent/challenge/football.csv", "Goals", "Goals Allowed");
        System.out.printf("Day with smallest temperature spread : %s%n", daysWithSmallestTemperatureSpread);
        System.out.printf("Team with smallest goal spread       : %s%n", teamsWithSmallestGoalDifference);
    }
}
