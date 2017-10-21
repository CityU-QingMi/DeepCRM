    public synchronized void setValue(int i, int value) {

        if (i < 0 || i >= count) {
            throw new IndexOutOfBoundsException();
        }

        if (sortOnValues) {
            sorted = false;
        }

        values[i] = value;
    }
