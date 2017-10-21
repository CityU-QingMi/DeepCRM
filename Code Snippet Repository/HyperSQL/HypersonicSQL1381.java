    NodeAVL last(PersistentStore store, NodeAVL x) {

        if (x == null) {
            return null;
        }

        RowAVL row = x.getRow(store);

        x = row.getNode(position);

        NodeAVL temp = x.getLeft(store);

        if (temp != null) {
            x    = temp;
            temp = x.getRight(store);

            while (temp != null) {
                x    = temp;
                temp = x.getRight(store);
            }

            return x;
        }

        temp = x;
        x    = x.getParent(store);

        while (x != null && x.isLeft(store, temp)) {
            temp = x;
            x    = x.getParent(store);
        }

        return x;
    }
