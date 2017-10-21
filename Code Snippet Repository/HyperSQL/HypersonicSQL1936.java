    public synchronized boolean addUnsorted(int key, int value) {

        if (count == capacity) {
            if (fixedSize) {
                return false;
            } else {
                doubleCapacity();
            }
        }

        if (sorted && count != 0) {
            if (sortOnValues) {
                if (value < values[count - 1]) {
                    sorted = false;
                }
            } else {
                if (key < keys[count - 1]) {
                    sorted = false;
                }
            }
        }

        hasChanged    = true;
        keys[count]   = key;
        values[count] = value;

        count++;

        return true;
    }
