    public static int find(short[] array, int value, int offset, int count) {

        for (int i = offset; i < offset + count; i++) {
            if (array[i] == value) {
                return i;
            }
        }

        return -1;
    }
