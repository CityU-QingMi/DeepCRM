    public void setNewNodes(PersistentStore store) {

        int indexCount = store.getAccessorKeys().length;

        nPrimaryNode = new NodeAVL(this);

        NodeAVL n = nPrimaryNode;

        for (int i = 1; i < indexCount; i++) {
            n.nNext = new NodeAVL(this);
            n       = n.nNext;
        }
    }
