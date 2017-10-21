    public synchronized boolean addUnsorted(DoubleIntIndex other) {

        if (count + other.count > capacity) {
            if (fixedSize) {
                return false;
            } else {
                while (count + other.count > capacity) {
                    doubleCapacity();
                }
            }
        }

        sorted     = false;
        hasChanged = true;

        for (int i = 0; i < other.count; i++) {
            keys[count]   = other.keys[i];
            values[count] = other.values[i];

            count++;
        }

        return true;
    }
