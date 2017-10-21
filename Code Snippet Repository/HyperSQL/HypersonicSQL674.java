    public void delete(PersistentStore store) {

        NodeAVL n = nPrimaryNode;

        while (n != null) {
            n.delete();

            n = n.nNext;
        }
    }
