    public static void sort(Object[] array, int start, int limit,
                            Comparator comparator) {

        if (start + 1 >= limit) {
            return;
        }

        quickSort(array, comparator, start, limit - 1);
        insertionSort(array, comparator, start, limit - 1);
    }
