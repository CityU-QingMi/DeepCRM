    public synchronized int add(int key) {

        if (count == capacity) {
            if (fixedSize) {
                return -1;
            } else {
                doubleCapacity();
            }
        }

        if (!sorted) {
            fastQuickSort();
        }

        targetSearchValue = key;

        int i = binarySlotSearch();

        if (count != i) {
            moveRows(i, i + 1, count - i);
        }

        keys[i] = key;

        count++;

        return i;
    }
