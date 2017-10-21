    public synchronized boolean addSorted(int key, int value) {

        if (count == capacity) {
            if (fixedSize) {
                return false;
            } else {
                doubleCapacity();
            }
        }

        if (count != 0) {
            if (sortOnValues) {
                if (value < values[count - 1]) {
                    return false;
                } else if (value == values[count - 1]
                           && key < keys[count - 1]) {
                    return false;
                }
            } else {
                if (key < keys[count - 1]) {
                    return false;
                }
            }
        }

        hasChanged    = true;
        keys[count]   = key;
        values[count] = value;

        count++;

        return true;
    }
