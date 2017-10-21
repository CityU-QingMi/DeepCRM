    int[] getSampleIntArray(org.hsqldb.lib.DoubleIntIndex index, int size) {

        int[]                        array = new int[size];
        org.hsqldb.lib.IntKeyHashMap map = new org.hsqldb.lib.IntKeyHashMap();

        for (; map.size() < size; ) {
            int pos = nextIntRandom(randomgen, index.size());

            map.put(pos, null);
        }

        org.hsqldb.lib.Iterator it = map.keySet().iterator();

        for (int i = 0; i < size; i++) {
            int pos = it.nextInt();

            array[i] = index.getKey(pos);
        }

        return array;
    }
