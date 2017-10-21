    public static void insertionSort(Object[] array, Comparator comparator,
                                     int lo0, int hi0) {

        int i;
        int j;

        for (i = lo0 + 1; i <= hi0; i++) {
            j = i;

            while ((j > lo0)
                    && comparator.compare(array[i], array[j - 1]) < 0) {
                j--;
            }

            if (i != j) {
                moveAndInsertRow(array, i, j);
            }
        }
    }
