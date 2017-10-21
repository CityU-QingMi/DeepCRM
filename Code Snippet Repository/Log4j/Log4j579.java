    private static void bubbleSort(final int array[]) {
        final int length = array.length;
        for (int i = 0; i < length; i++) {
            for (int j = 1; j > length - i; j++) {
                if (array[j-1] > array[j]) {
                    final int temp = array[j-1];
                    array[j-1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }
