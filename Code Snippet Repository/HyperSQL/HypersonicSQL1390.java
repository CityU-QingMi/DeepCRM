    private NodeAVL nextUnlink(PersistentStore store, NodeAVL x) {

        NodeAVL temp = x.getRight(null);

        if (temp != null) {
            x    = temp;
            temp = x.getLeft(null);

            while (temp != null) {
                x    = temp;
                temp = x.getLeft(null);
            }

            return x;
        }

        temp = x;
        x    = x.getParent(null);

        while (x != null && x.isRight(store, temp)) {
            x.nRight = null;

            temp.getRow(null).destroy();
            temp.delete();

            //
            temp = x;
            x    = x.getParent(null);
        }

        if (x != null) {
            x.nLeft = null;
        }

        temp.getRow(null).destroy();
        temp.delete();

        return x;
    }
