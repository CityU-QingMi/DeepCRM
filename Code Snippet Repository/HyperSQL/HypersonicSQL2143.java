    public int[] toArray() {

        int   lookup = -1;
        int[] array  = new int[size()];

        for (int i = 0; i < array.length; i++) {
            lookup = super.nextLookup(lookup);

            int value = intKeyTable[lookup];

            array[i] = value;
        }

        return array;
    }
