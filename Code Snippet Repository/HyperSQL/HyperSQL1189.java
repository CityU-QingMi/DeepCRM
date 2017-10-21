    public Object get(int key) {

        try {
            readLock.lock();

            int lookup = getLookup(key);

            if (lookup != -1) {
                return objectValueTable[lookup];
            }

            return null;
        } finally {
            readLock.unlock();
        }
    }
