    public HashIndex(int hashTableSize, int capacity, boolean fixedSize) {

        if (capacity < hashTableSize) {
            capacity = hashTableSize;
        }

        reset(hashTableSize, capacity);

        this.fixedSize = fixedSize;
    }
