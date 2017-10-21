    private int partition(int start, int end, int pivot) {

        int store = start;

        swap(pivot, end);

        for (int i = start; i <= end - 1; i++) {
            if (lessThan(i, end)) {
                swap(i, store);

                store++;
            }
        }

        swap(store, end);

        return store;
    }
