package datamunging;

import lombok.Data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static java.util.logging.Level.INFO;

/**
 * Given football.dat, write a program to print the name of the team
 * with the smallest difference in ‘for’ and ‘against’ goals.
 */
public class Football {

    private static final Logger LOGGER =  Logger.getLogger(Football.class.getName());
    private static final String INPUT_PATH = "/datamunging/football.dat";

    @Data
    static class Team {
        private String name;
        private int goalFor;
        private int goalAgainst;

        int getDifference() {
            return goalFor - goalAgainst;
        }
    }

    public static void main(String []args) {
        final InputStream inputStream = Football.class.getResourceAsStream(INPUT_PATH);
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try(Stream<String> stream = reader.lines()) { // buffered reader will close the underlying input stream as well
            Optional<Team> team = stream
                .map(readLine())
                .filter(t -> t.getName() != null)
                .min(Comparator.comparing(Team::getDifference));

            team.ifPresent(d -> LOGGER.log(INFO, "The team with smallest difference is {0}", d.getName()));
        }
    }

    private static Function<String, Team> readLine() {
        return line -> {
            final String[] tokens = line.split("\\s+");

            final Team team = new Team();
            if (tokens.length < 10) {
                team.setName(null);
            } else {
                team.setName(tokens[2]);
                team.setGoalFor(parseInt(tokens[7]));
                team.setGoalAgainst(parseInt(tokens[9]));
            }
            return team;
        };
    }

    private static int parseInt(final String s) {
        final String s1 = s.replaceAll("\\*", "");

        try {
            return Integer.parseInt(s1);
        } catch (NumberFormatException | NullPointerException e) {
            return -1;
        }
    }
}
