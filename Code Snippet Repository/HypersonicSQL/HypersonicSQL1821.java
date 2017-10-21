    static void quickSort(Object[] array, Comparator comparator, int l,
                          int r) {

        int M = 16;
        int i;
        int j;
        int v;

        if ((r - l) > M) {
            i = (r + l) >>> 1;

            if (comparator.compare(array[i], array[l]) < 0) {
                swap(array, l, i);    // Tri-Median Method!
            }

            if (comparator.compare(array[r], array[l]) < 0) {
                swap(array, l, r);
            }

            if (comparator.compare(array[r], array[i]) < 0) {
                swap(array, i, r);
            }

            j = r - 1;

            swap(array, i, j);

            i = l;
            v = j;

            for (;;) {
                while (comparator.compare(array[++i], array[v]) < 0) {}

                while (comparator.compare(array[v], array[--j]) < 0) {}

                if (j < i) {
                    break;
                }

                swap(array, i, j);
            }

            swap(array, i, r - 1);
            quickSort(array, comparator, l, j);
            quickSort(array, comparator, i + 1, r);
        }
    }
