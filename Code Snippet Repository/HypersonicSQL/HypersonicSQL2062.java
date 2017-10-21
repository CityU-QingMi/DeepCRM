    public synchronized boolean addUnique(int key) {

        if (count == capacity) {
            if (fixedSize) {
                return false;
            } else {
                doubleCapacity();
            }
        }

        if (!sorted) {
            fastQuickSort();
        }

        targetSearchValue = key;

        int i = binaryEmptySlotSearch();

        if (i == -1) {
            return false;
        }

        if (count != i) {
            moveRows(i, i + 1, count - i);
        }

        keys[i] = key;

        count++;

        return true;
    }
