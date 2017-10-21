    public void destroy() {

        JavaSystem.memoryRecords++;

        clearNonPrimaryNodes();

        NodeAVL n = nPrimaryNode;

        while (n != null) {
            NodeAVL last = n;

            n          = n.nNext;
            last.nNext = null;
        }
    }
