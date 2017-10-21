    public void setNewNodes(PersistentStore store) {

        int index = store.getAccessorKeys().length;

        nPrimaryNode = new NodeAVL(this);

        NodeAVL n = nPrimaryNode;

        for (int i = 1; i < index; i++) {
            n.nNext = new NodeAVL(this);
            n       = n.nNext;
        }
    }
