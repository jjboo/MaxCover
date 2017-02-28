package ca.ubc.maxcover.node;

import java.util.Comparator;

public class CelfNode extends AbstractNode {

    public static class NodeComparator implements Comparator<CelfNode> {
        @Override
        public int compare(CelfNode n1, CelfNode n2){
            return Integer.compare(n2.mg, n1.mg); // sort in DESC order
        }
    }

    public int mg;
    public int flag;

    /**
     * Default constructor, should not be called
     */
    protected CelfNode() {
        this.id = -1;
        this.mg = -Integer.MAX_VALUE;
        this.flag = -1;
    }

    /**
     * Public constructor
     */
    public CelfNode(int id, int mg, int flag) {
        this.id = id;
        this.mg = mg;
        this.flag = flag;
    }
}
