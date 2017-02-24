package ca.ubc.maxcover.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Config class to set up execution parameters
 */
public class Config {

    private static final Logger LOGGER = Logger.getLogger("MaxCover");

    private String dataFile;
    private String outputDir;
    private int numSeeds;
    private Algorithm algorithm;
    private int startIter;

    public enum Algorithm {
        CELF("celf"),
        CELFPP("celfplus");

        private final String name;

        Algorithm(String s) {
            name = s;
        }

        public String toString() {
            return this.name;
        }
    }

    /**
     * Constructor
     */
    public Config(String configFile, String dataFile, String outputDir) {
        Properties prop = new Properties();
        try {
            InputStream input = new FileInputStream(configFile);
            prop.load(input); // load a properties file
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        this.dataFile = dataFile;
        this.outputDir = outputDir;
        this.numSeeds = Integer.parseInt(prop.getProperty("numSeeds", "50"));
        setAlgorithm(prop.getProperty("algo", "celf"));
        this.startIter = Integer.parseInt(prop.getProperty("startIter", "2"));
    }


    public String getDataFile() {
        return this.dataFile;
    }

    public String getOutputDir() {
        return this.outputDir;
    }

    public int getNumSeeds() {
        return this.numSeeds;
    }

    public Algorithm getAlgorithm() {
        return this.algorithm;
    }

    public int getStartIter() {
        return this.startIter;
    }

    /**
     * Set which algorithm to use
     */
    private void setAlgorithm(String algo) {
        if (algo.toLowerCase().equals("celf")) {
            algorithm = Algorithm.CELF;
        } else if (algo.toLowerCase().equals("celfpp")) {
            algorithm = Algorithm.CELFPP;
        } else {
            throw new RuntimeException("Algorithm not supported. " + algo);
        }
    }

    /**
     * Print the parameters for this exec
     */
    public void print() {
        LOGGER.info("Graph file path: " + this.dataFile);
        LOGGER.info("Output location: " + this.outputDir);
        LOGGER.info("Algorithm choice: " + this.algorithm);
        LOGGER.info("Number of seeds: " + this.numSeeds);
        LOGGER.info("Starting iteration: " + this.startIter);
    }
}
