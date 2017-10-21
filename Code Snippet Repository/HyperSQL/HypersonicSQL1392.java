    NodeAVL next(PersistentStore store, NodeAVL x) {

        NodeAVL r = x.nRight;

        if (r != null) {
            x = r;

            NodeAVL l = x.nLeft;

            while (l != null) {
                x = l;
                l = x.nLeft;
            }

            return x;
        }

        NodeAVL ch = x;

        x = x.nParent;

        while (x != null && ch == x.nRight) {
            ch = x;
            x  = x.nParent;
        }

        return x;
    }
