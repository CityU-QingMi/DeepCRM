    public int getOrderedMatchCount(int[] array) {

        int i = 0;

        readLock.lock();

        try {
            for (; i < array.length; i++) {
                if (!super.containsKey(array[i])) {
                    break;
                }
            }
        } finally {
            readLock.unlock();
        }

        return i;
    }
