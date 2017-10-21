    private static void initPool() {

        int[] sizeArray  = defaultPoolLookupSize;
        int   sizeFactor = defaultSizeFactor;

        synchronized (ValuePool.class) {
            maxStringLength = defaultMaxStringLength;
            poolList        = new ValuePoolHashMap[POOLS_COUNT];

            for (int i = 0; i < POOLS_COUNT; i++) {
                int size = sizeArray[i];

                poolList[i] = new ValuePoolHashMap(size, size * sizeFactor,
                                                   BaseHashMap.PURGE_HALF);
            }

            intPool        = poolList[0];
            longPool       = poolList[1];
            doublePool     = poolList[2];
            bigdecimalPool = poolList[3];
            stringPool     = poolList[4];
        }
    }
