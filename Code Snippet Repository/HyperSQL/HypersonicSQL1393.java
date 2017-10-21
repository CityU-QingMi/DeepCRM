    NodeAVL last(PersistentStore store, NodeAVL x) {

        if (x == null) {
            return null;
        }

        NodeAVL left = x.nLeft;

        if (left != null) {
            x = left;

            NodeAVL right = x.nRight;

            while (right != null) {
                x     = right;
                right = x.nRight;
            }

            return x;
        }

        NodeAVL ch = x;

        x = x.nParent;

        while (x != null && ch.equals(x.nLeft)) {
            ch = x;
            x  = x.nParent;
        }

        return x;
    }
