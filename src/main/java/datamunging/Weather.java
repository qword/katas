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
 * Given weather.dat, write a program to output the day number (column one) with the smallest
 * temperature spread (the maximum temperature is the second column, the minimum the third column).
 */
public class Weather {

    private static final Logger LOGGER =  Logger.getLogger(Weather.class.getName());
    private static final String INPUT_PATH = "/datamunging/weather.dat";

    @Data
    static class DayTemp {
        private int dayNumber;
        private int maxTemp;
        private int minTemp;

        int getSpread() {
            return maxTemp - minTemp;
        }
    }

    public static void main(String []args) {
        final InputStream inputStream = Weather.class.getResourceAsStream(INPUT_PATH);
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try(Stream<String> stream = reader.lines()) { // buffered reader will close the underlying input stream as well
            Optional<DayTemp> dayTempOpt = stream
                .map(readLine())
                .filter(dayTemp -> dayTemp.getDayNumber() > 0)
                .min(Comparator.comparing(DayTemp::getSpread));

            dayTempOpt.ifPresent(d -> LOGGER.log(INFO, "The day with the smallest spread is {0}", d.getDayNumber()));
        }
    }

    private static Function<String, DayTemp> readLine() {
        return line -> {
            final String[] tokens = line.split("\\s+");

            final DayTemp dayTemp = new DayTemp();
            if (tokens.length < 2) {
                dayTemp.setDayNumber(-1);
            } else {
                dayTemp.setDayNumber(parseInt(tokens[1]));
                dayTemp.setMaxTemp(parseInt(tokens[2]));
                dayTemp.setMinTemp(parseInt(tokens[3]));
            }
            return dayTemp;
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
