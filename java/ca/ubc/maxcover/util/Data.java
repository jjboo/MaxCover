package ca.ubc.maxcover.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Holding input data
 */
public class Data {

    private static final Logger LOGGER = Logger.getLogger("MaxCover");

    private List<Set<Integer>> transactions;

    /**
     * Constructor
     */
    public Data(String file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        transactions = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            String[] nums = line.trim().split(" ");
            Set<Integer> set = new HashSet<>(nums.length);
            for (String n : nums) {
                int item = Integer.parseInt(n);
                set.add(item);
            }
            transactions.add(set);
        }

        br.close();
        LOGGER.info("Finished reading input data");
        LOGGER.info("Number of transactions: " + transactions.size());
    }

    public List<Set<Integer>> getTransactions() {
        return this.transactions;
    }
}
