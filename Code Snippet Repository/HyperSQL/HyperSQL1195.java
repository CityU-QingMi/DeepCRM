    public int getOrderedMatchCount(int[] array) {

        int i = 0;

        try {
            readLock.lock();

            for (; i < array.length; i++) {
                if (!super.containsKey(array[i])) {
                    break;
                }
            }

            return i;
        } finally {
            readLock.unlock();
        }
    }
