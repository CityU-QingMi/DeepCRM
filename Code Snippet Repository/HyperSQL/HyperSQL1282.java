    public long[] toArray() {

        int   lookup = -1;
        long[] array  = new long[size()];

        for (int i = 0; i < array.length; i++) {
            lookup = super.nextLookup(lookup);

            long value = intKeyTable[lookup];

            array[i] = value;
        }

        return array;
    }
