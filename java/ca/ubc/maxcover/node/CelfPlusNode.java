package ca.ubc.maxcover.node;

/**
 * Created by weilu on 2/23/17.
 */
public class CelfPlusNode extends CelfNode {

    public int mg2;
    public int prevBest;

    public CelfPlusNode(int id, int mg, int mg2, int flag, int prevBest) {
        this.id = id;
        this.mg = mg;
        this.mg2 = mg2;
        this.flag = flag;
        this.prevBest = prevBest;
    }
}
