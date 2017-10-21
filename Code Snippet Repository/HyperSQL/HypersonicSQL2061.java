    public synchronized boolean addSorted(int key) {

        if (count == capacity) {
            if (fixedSize) {
                return false;
            } else {
                doubleCapacity();
            }
        }

        if (count != 0) {
            if (key < keys[count - 1]) {
                return false;
            }
        }

        keys[count] = key;

        count++;

        return true;
    }
