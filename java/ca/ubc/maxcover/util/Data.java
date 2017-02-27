package ca.ubc.maxcover.util;

import ca.ubc.maxcover.Main;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Holding input data
 */
public class Data {
    private List<Set<Integer>> transactions;

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
        Main.LOGGER.info("Finished reading input data");
        Main.LOGGER.info("Number of transactions: " + transactions.size());
    }

    public Set<Integer> getById(int id) {
        return this.transactions.get(id);
    }

    public int size() {
        return this.transactions.size();
    }
}
