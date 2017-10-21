    public void unlinkNodes(PersistentStore store, NodeAVL primaryRoot) {

        NodeAVL x = primaryRoot;
        NodeAVL l = x;

        while (l != null) {
            x = l;
            l = x.getLeft(null);
        }

        while (x != null) {
            NodeAVL n = nextUnlink(store, x);

            x = n;
        }
    }
