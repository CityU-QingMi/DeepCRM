    public void releaseRange(long startPos, long limitPos) {

        objectIterator.reset();

        while (objectIterator.hasNext()) {
            CachedObject o   = (CachedObject) objectIterator.next();
            long         pos = o.getPos();

            if (pos >= startPos && pos < limitPos) {
                o.setInMemory(false);
                objectIterator.remove();

                cacheBytesLength -= o.getStorageSize();
            }
        }
    }
