package ca.ubc.maxcover.algo;

import ca.ubc.maxcover.Main;
import ca.ubc.maxcover.node.CelfNode;
import ca.ubc.maxcover.node.CelfPlusNode;
import ca.ubc.maxcover.util.Config;
import ca.ubc.maxcover.util.Data;
import ca.ubc.maxcover.util.Utility;
import java.io.BufferedWriter;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;


/**
 * CELF++ algorithm implementation
 */
public class CelfPlus {

  private PriorityQueue<CelfPlusNode> _covQueue;
  private Data _data;
  private Config _config;
  private Set<Integer> _seedSet;
  private BufferedWriter _bufferedWriter;

  private static final int INITIAL_MG = 0;
  private static final int INITIAL_FLAG = 0;
  private static final int NULL_ID = -Integer.MAX_VALUE;

  /**
   * Constructor
   */
  public CelfPlus(Data data, Config config, BufferedWriter bufferedWriter) {
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
    int savings = 0;
    _seedSet.clear();
    int lastSeedId = NULL_ID; // id of the last chosen seed
    int curBestId = NULL_ID;  // id of the current highest MG node in the priority queue
    double curBestMg = 0; // MG(curBestId | S)
    Set<Integer> covered = new HashSet<>();

    final long startTime = System.currentTimeMillis();

    // first iteration
    for (int id = 0; id < _data.size(); ++id) {
      CelfPlusNode node = new CelfPlusNode(id, _data.getById(id).size(), INITIAL_MG, INITIAL_FLAG, curBestId);
      _covQueue.add(node);
      lookUps++;

      if (id % 1000 == 0) {
        Main.LOGGER.info(node.id + "\t" + String.format("%d", node.mg));
      }
    }

    /* Select k seeds */
    while (_seedSet.size() < _config.getNumSeeds()) {

      CelfPlusNode bestNode = _covQueue.peek();

      // flag = current seed set size: found a seed
      if (bestNode.flag == _seedSet.size()) {
        _seedSet.add(bestNode.id);
        lastSeedId = bestNode.id;
        coverage += bestNode.mg;

        double timeInSec = (double) (System.currentTimeMillis() - startTime) / 1000.0;
        Main.LOGGER.info(Utility.seedToStr(_seedSet.size(), bestNode.id, bestNode.mg, coverage,
            lookUps, 0, timeInSec));

        _covQueue.poll();
        lookUps = 0;
        savings = 0;
        curBestId = NULL_ID;
        curBestMg = 0;

      } else {
        CelfPlusNode newNode; // wait to be MG updated and re-heapifying

        if (bestNode.prevBest == lastSeedId && bestNode.flag == _seedSet.size() - 1) {
          // u.mg2 = u.mg1; skip MG re-computation
          savings++;
          newNode = new CelfPlusNode(bestNode.id, bestNode.mg2, 0, _seedSet.size(), NULL_ID);

        } else if (_seedSet.contains(bestNode.prevBest) && bestNode.flag <= _seedSet.size() - 1)  {
          savings++;
          newNode = new CelfPlusNode(bestNode.id, bestNode.mg2, 0, _seedSet.size() - 1, NULL_ID);

        } else {
          // need to do MG recomputation
          lookUps++;
          int[] gains = SetCover.computeMG2(_data.getById(bestNode.id), covered,
              _data.getById(bestNode.prevBest));
          newNode = new CelfPlusNode(bestNode.id, gains[0], gains[1], _seedSet.size(), curBestId);
        }

        // Re-heapify
        _covQueue.poll();
        _covQueue.add(newNode);

        // Update current Best. If mg is same, then use the node ids. Smaller node ids are preferred.
        if (newNode.mg > curBestMg || (newNode.mg == curBestMg && newNode.id < curBestId)) {
          curBestId = newNode.id;
          curBestMg = newNode.mg;
        }
      }
    }

    return coverage;
  }

}
