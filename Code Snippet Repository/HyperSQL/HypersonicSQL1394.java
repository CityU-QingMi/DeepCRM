    public NodeAVL set(PersistentStore store, boolean isLeft, NodeAVL n) {

        if (isLeft) {
            nLeft = n;
        } else {
            nRight = n;
        }

        if (n != null) {
            n.nParent = this;
        }

        return this;
    }
