    int probeFactor(Session session, PersistentStore store, double[] changes,
                    boolean left) {

        int     depth = 0;
        NodeAVL x     = getAccessor(store);
        NodeAVL n     = x;

        if (x == null) {
            return 0;
        }

        while (n != null) {
            x = n;
            n = left ? x.getLeft(store)
                     : x.getRight(store);

            depth++;

            if (depth > probeDepth && n != null) {
                compareRowForChange(session, x.getData(store),
                                    n.getData(store), changes);
            }
        }

        return depth - probeDepth;
    }
