package ca.ubc.maxcover;

import ca.ubc.maxcover.util.Config;
import java.util.logging.Logger;

/**
 * Main class for executing jobs
 */
public class Main {
    public static final Logger LOGGER = Logger.getLogger("MaxCover");

    private static final int NUM_ARGS = 3;

    public static void main(String[] args) {
        if (args.length != NUM_ARGS) {
            StringBuilder sb = new StringBuilder();
            sb.append("Usage: java -classpath MaxCover.jar ca.ubc.maxcover.Main [configFile] [graphFile] [outputDir]\n");
            sb.append("[configFile]: path to a config property file");
            sb.append("[dataFile]: path to the input graph file (dataset)");
            sb.append("[outputDir]: location to store output logs");
            System.out.println(sb.toString());
            System.exit(1);
        }

        LOGGER.info("Command line arguments: " + args[0] + " " + args[1] + " " + args[2]);
        Config config = new Config(args[0], args[1], args[2]);
        run(config);
    }

    /**
     *
     * @param config Config instance with all relevant parameters set
     */
    private static void run(Config config) {
    }
}
