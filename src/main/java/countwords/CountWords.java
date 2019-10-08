package countwords;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.WARNING;

public class CountWords {

    private static final Logger LOGGER =  Logger.getLogger(CountWords.class.getName());
    private static final String INPUT_PATH = "/countwords/input.txt";

    /**
     * Test 1: iterative, naive
     * Test 2: Java 8 stream
     * Test 3: NIO
     *
     */
    public static void main(String[] args) {
        LOGGER.log(INFO, "countWords1: {0}", countWords1());
        LOGGER.log(INFO, "countWords2: {0}", countWords2());
        LOGGER.log(INFO, "countWords3: {0}", countWords3());
    }

    private static int countWords1() {
        int count = 0;

        final InputStream inputStream = CountWords.class.getResourceAsStream(INPUT_PATH);
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String line = reader.readLine();
            while (line != null) {
                count += line.split(" ").length;
                line = reader.readLine();
            }
        } catch (final IOException e) {
            LOGGER.log(WARNING, "Error while reading: {0}", e.getMessage());
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                LOGGER.log(WARNING, "Error while closing: {0}", e.getMessage());
            }
        }
        return count;
    }

    private static int countWords2() {
        final InputStream inputStream = CountWords.class.getResourceAsStream(INPUT_PATH);
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try(Stream<String> stream = reader.lines()) { // buffered reader will close the underlying input stream as well
            return stream.mapToInt(line -> line.split(" ").length).sum();
        }
    }

    private static int countWords3() {
        try {
            final Path path = Paths.get(Objects.requireNonNull(CountWords.class.getResource(INPUT_PATH)).toURI());
            try(Stream<String> stream = Files.lines(path)) {
                return stream.mapToInt(line -> line.split(" ").length).sum();
            }
        } catch (URISyntaxException | IOException e) {
            LOGGER.log(WARNING, "Error while reading: {0}", e.getMessage());
        }
        return -1;
    }
}
