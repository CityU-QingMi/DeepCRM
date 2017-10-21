    public static void resetPool(int[] sizeArray, int sizeFactor) {

        synchronized (ValuePool.class) {
            for (int i = 0; i < POOLS_COUNT; i++) {
                poolList[i].clear();
                poolList[i].resetCapacity(sizeArray[i] * sizeFactor,
                                          BaseHashMap.PURGE_HALF);
            }
        }
    }
