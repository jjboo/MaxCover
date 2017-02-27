package ca.ubc.maxcover.algo;

import ca.ubc.maxcover.Main;
import ca.ubc.maxcover.node.CelfNode;
import ca.ubc.maxcover.util.Config;
import ca.ubc.maxcover.util.Data;

import ca.ubc.maxcover.util.Utility;
import java.io.BufferedWriter;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;


/**
 * CELF algorithm implementation
 */
public class Celf {

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
     * Mine seeds
     */
    public int run() {
        int coverage = 0;
        int lookUps = 0;
        _seedSet.clear();
        Set<Integer> covered = new HashSet<>();

        final long startTime = System.currentTimeMillis();

        /* first iteration */
        for (int id = 0; id < _data.size(); ++id) {
            CelfNode node = new CelfNode(id, _data.getById(id).size(), INITIAL_FLAG);
            _covQueue.add(node);
            lookUps++;

            if (id % 1000 == 0) {
                Main.LOGGER.info(node.id + "\t" + String.format("%d", node.mg));
            }
        }

        /* Select k seeds */
        while (_seedSet.size() < _config.getNumSeeds()) {
            CelfNode bestNode = _covQueue.peek();

            // flag = current seed set size: found a seed
            if (bestNode.flag == _seedSet.size()) {
                _seedSet.add(bestNode.id);
                coverage += bestNode.mg;
                for (int element : _data.getById(bestNode.id)) {
                    covered.add(element);
                }
                _covQueue.poll();
                double timeInSec = (double) (System.currentTimeMillis() - startTime) / 1000.0;
                Main.LOGGER.info(Utility.seedToStr(_seedSet.size(), bestNode.id, bestNode.mg, coverage,
                    lookUps, 0, timeInSec));
                lookUps = 0; // reset
            } else {
                // re-compute MG
                lookUps++;
                int newMg = SetCover.computeMG(_data.getById(bestNode.id), covered);
                // update flag and re-heapify
                int id = bestNode.id;
                _covQueue.poll();
                _covQueue.add(new CelfNode(id, newMg, _seedSet.size()));
            }
        }

        return coverage;
    }
}
