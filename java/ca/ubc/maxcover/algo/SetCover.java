package ca.ubc.maxcover.algo;

import java.util.Set;

/**
 * Given a family of sets, compute how many ground-set elements it is covered
 */
public class SetCover {

    /**
     * Compute marginal gain MG(X|S)
     * @param candidate A seed candidate
     * @param covered Items covered by the current seed set
     */
    public static int computeMG(Set<Integer> candidate, Set<Integer> covered) {
        int ret = 0;
        for (int i : candidate) {
            if (!covered.contains(i)) {
                ret++;
            }
        }
        return ret;
    }

    /**
     * Compute marginal gain MG(X|S+X.best)
     * @param candidate A seed candidate
     * @param covered Items covered by the current seed set
     * @param preBest candidate's prev best set
     */
    public static int[] computeMG2(Set<Integer> candidate, Set<Integer> covered, Set<Integer> preBest) {
        int[] ret = new int[2];
        for (int i : candidate) {
            if (!covered.contains(i)) {
                ret[0]++;
                if (!preBest.contains(i)) {
                    ret[1]++;
                }
            }
        }
        return ret;
    }
}
