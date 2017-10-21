    public Object get(long key) {

        readLock.lock();

        try {
            int lookup = getLookup(key);

            if (lookup != -1) {
                return objectValueTable[lookup];
            }

            return null;
        } finally {
            readLock.unlock();
        }
    }
