    public NodeAVL set(PersistentStore store, boolean isLeft, NodeAVL n) {

        NodeAVL x;

        if (isLeft) {
            x = setLeft(store, n);
        } else {
            x = setRight(store, n);
        }

        if (n != null) {
            n.setParent(store, x);
        }

        return x;
    }
