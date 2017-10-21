    public void replace(PersistentStore store, Index index, NodeAVL n) {

        if (nParent == null) {
            if (n != null) {
                n = n.setParent(store, null);
            }

            store.setAccessor(index, n);
        } else {
            nParent.set(store, isFromLeft(store), n);
        }
    }
