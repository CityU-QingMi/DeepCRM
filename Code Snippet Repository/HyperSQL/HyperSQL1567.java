    public void releaseRange(IntIndex list, int fileBlockItemCount) {

        objectIterator.reset();

        while (objectIterator.hasNext()) {
            CachedObject o     = (CachedObject) objectIterator.next();
            long         pos   = o.getPos();
            int          block = (int) (pos / fileBlockItemCount);
            int          index = list.findFirstEqualKeyIndex(block);

            if (index >= 0) {
                o.setInMemory(false);
                objectIterator.remove();

                cacheBytesLength -= o.getStorageSize();
            }
        }
    }
