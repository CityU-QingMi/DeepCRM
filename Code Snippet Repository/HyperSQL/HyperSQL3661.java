    public void setNewNodes(PersistentStore store) {

        int indexcount = store.getAccessorKeys().length;

        nPrimaryNode = new NodeAVLDisk(this, 0);

        NodeAVL n = nPrimaryNode;

        for (int i = 1; i < indexcount; i++) {
            n.nNext = new NodeAVLDisk(this, i);
            n       = n.nNext;
        }
    }
