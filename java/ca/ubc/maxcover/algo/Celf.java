package ca.ubc.maxcover.algo;

import ca.ubc.maxcover.node.CelfNode;
import ca.ubc.maxcover.util.Config;
import ca.ubc.maxcover.util.Data;

import java.io.BufferedWriter;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.logging.Logger;


/**
 * CELF algorithm implementation
 */
public class Celf {

    private static final Logger LOGGER = Logger.getLogger("MaxCover");

    private PriorityQueue<CelfNode> _covQueue;
    private Data _data;
    private Config _config;
    private Set<Integer> _seedSet;
    private BufferedWriter _bufferedWriter;

    private static final int INITIAL_FLAG = 0;

    /**
     * Constructor
     */
    public Celf(Data data, Config config, BufferedWriter bufferedWriter) {
        _config = config;
        _data = data;
        _covQueue = new PriorityQueue<>(new CelfNode.NodeComparator());
        _seedSet = new HashSet<>();
        _bufferedWriter = bufferedWriter;
    }

    /**
     * Executing greedy algorithm
     */
    public int run() {
        return 0;
    }

}
