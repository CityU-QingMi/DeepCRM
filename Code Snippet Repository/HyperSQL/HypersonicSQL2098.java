    public long[] toArray() {

        long[] array = new long[elementCount];

        for (int i = 0; i < elementCount; i++) {
            array[i] = get(i);
        }

        return array;
    }
