    public void replace(PersistentStore store, Index index, NodeAVL n) {

        NodeAVLDiskLarge node = findNode(store);

        if (node.iParent == NO_POS) {
            if (n != null) {
                n = n.setParent(store, null);
            }

            store.setAccessor(index, n);
        } else {
            boolean isFromLeft = node.isFromLeft(store);

            node.getParent(store).set(store, isFromLeft, n);
        }
    }
