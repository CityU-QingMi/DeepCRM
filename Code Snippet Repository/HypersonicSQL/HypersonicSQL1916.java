    public synchronized int add(int key, int value) {

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

        targetSearchValue = sortOnValues ? value
                                         : key;

        int i = binarySlotSearch(true);

        hasChanged = true;

        if (count != i) {
            moveRows(i, i + 1, count - i);
        }

        keys[i]   = key;
        values[i] = value;

        count++;

        return i;
    }
