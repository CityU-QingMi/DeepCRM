    public NodeAVL getNode(int index) {

        NodeAVL n = nPrimaryNode;

        while (index-- > 0) {
            n = n.nNext;
        }

        return n;
    }
