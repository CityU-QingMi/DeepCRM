    public static int findNot(int[] array, int value) {

        for (int i = 0; i < array.length; i++) {
            if (array[i] != value) {
                return i;
            }
        }

        return -1;
    }
