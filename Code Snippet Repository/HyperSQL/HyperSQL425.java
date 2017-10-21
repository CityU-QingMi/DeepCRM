    NodeAVL next(PersistentStore store, NodeAVL x, int depth, int maxDepth,
                 int[] depths) {

        NodeAVL temp = depth == maxDepth ? null
                                         : x.getRight(store);

        if (temp != null) {
            depth++;

            x    = temp;
            temp = depth == maxDepth ? null
                                     : x.getLeft(store);

            while (temp != null) {
                depth++;

                x = temp;

                if (depth == maxDepth) {
                    temp = null;
                } else {
                    temp = x.getLeft(store);
                }
            }

            depths[0] = depth;

            return x;
        }

        temp = x;
        x    = x.getParent(store);

        depth--;

        while (x != null && x.isRight(store, temp)) {
            temp = x;
            x    = x.getParent(store);

            depth--;
        }

        depths[0] = depth;

        return x;
    }
