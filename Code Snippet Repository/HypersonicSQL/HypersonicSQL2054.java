    private synchronized void fastQuickSort() {

        DoubleIntIndex indices   = new DoubleIntIndex(32, false);
        int            threshold = 16;

        indices.push(0, count - 1);

        while (indices.size() > 0) {
            int start = indices.peekKey();
            int end   = indices.peekValue();

            indices.pop();

            if (end - start >= threshold) {
                int pivot = partition(start, end,
                                      start + ((end - start) >>> 1));

                indices.push(start, pivot - 1);
                indices.push(pivot + 1, end);
            } else {
                insertionSort(start, end);
            }
        }

        sorted = true;
    }
