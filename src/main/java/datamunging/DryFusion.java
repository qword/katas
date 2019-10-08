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
 * Take the two programs written previously and factor out as much common code as possible,
 * leaving you with two smaller programs and some kind of shared functionality.
 */
public class DryFusion {
    private static final Logger LOGGER =  Logger.getLogger(Football.class.getName());
    private static final String FOOTBALL_PATH = "/datamunging/football.dat";
    private static final String WEATHER_PATH = "/datamunging/weather.dat";

    public static void main(String[] args) {
        calculateWeather();
        calculateFootball();
    }

    private static void calculateWeather() {
        final Optional<Diff> diff = read(WEATHER_PATH, line -> {
            final String[] tokens = line.split("\\s+");

            final Diff d = new Diff();
            if (tokens.length < 2 || parseInt(tokens[1]) == -1) {
                d.setName(null);
            } else {
                d.setName(tokens[1]);
                d.setMax(parseInt(tokens[2]));
                d.setMin(parseInt(tokens[3]));
            }
            return d;
        });

        diff.ifPresent(d -> LOGGER.log(INFO, "The day with the smallest spread is {0}", d.getName()));
    }


    private static void calculateFootball() {
        final Optional<Diff> diff = read(FOOTBALL_PATH, line -> {
            final String[] tokens = line.split("\\s+");

            final Diff d = new Diff();
            if (tokens.length < 10) {
                d.setName(null);
            } else {
                d.setName(tokens[2]);
                d.setMax(parseInt(tokens[7]));
                d.setMin(parseInt(tokens[9]));
            }
            return d;
        });
        diff.ifPresent(d -> LOGGER.log(INFO, "The team with smallest difference is {0}", d.getName()));
    }

    @Data
    static class Diff {
        private String name;
        private int max;
        private int min;
        int getDiff() {
            return max - min;
        }
    }

    private static BufferedReader readFile(final String path) {
        final InputStream inputStream = Football.class.getResourceAsStream(path);
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    private static Optional<Diff> read(final String path, Function<String, Diff> readLine) {
        final BufferedReader bufferedReader = readFile(path);
        try(Stream<String> stream = bufferedReader.lines()) { // buffered reader will close the underlying input stream as well
            return stream
                .map(readLine)
                .filter(t -> t.getName() != null)
                .min(Comparator.comparing(Diff::getDiff));
        }
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
