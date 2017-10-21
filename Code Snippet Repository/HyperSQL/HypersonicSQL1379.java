    NodeAVL next(PersistentStore store, NodeAVL x) {

        if (x == null) {
            return null;
        }

        RowAVL row = x.getRow(store);

        x = row.getNode(position);

        NodeAVL temp = x.getRight(store);

        if (temp != null) {
            x    = temp;
            temp = x.getLeft(store);

            while (temp != null) {
                x    = temp;
                temp = x.getLeft(store);
            }

            return x;
        }

        temp = x;
        x    = x.getParent(store);

        while (x != null && x.isRight(store, temp)) {
            temp = x;
            x    = x.getParent(store);
        }

        return x;
    }
