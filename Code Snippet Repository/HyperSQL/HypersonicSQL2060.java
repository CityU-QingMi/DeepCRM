    public synchronized boolean addUnsorted(int key) {

        if (count == capacity) {
            if (fixedSize) {
                return false;
            } else {
                doubleCapacity();
            }
        }

        if (sorted && count != 0) {
            if (key < keys[count - 1]) {
                sorted = false;
            }
        }

        keys[count] = key;

        count++;

        return true;
    }
