    public void clearNonPrimaryNodes() {

        NodeAVL n = nPrimaryNode.nNext;

        while (n != null) {
            n.delete();

            n = n.nNext;
        }
    }
