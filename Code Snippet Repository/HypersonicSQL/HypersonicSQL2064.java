    public synchronized int findFirstIndexUnsorted(int value) {

        for (int i = 0; i < count; i++) {
            if (keys[i] == value) {
                return i;
            }
        }

        return -1;
    }
