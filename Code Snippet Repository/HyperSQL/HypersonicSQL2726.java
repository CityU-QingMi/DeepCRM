    public long elementCount() {

        Index index = this.indexList[0];

        if (elementCount.get() < 0) {
            readLock();

            try {
                elementCount.set(index.getNodeCount(null, this));
            } finally {
                readUnlock();
            }
        }

        return elementCount.get();
    }
