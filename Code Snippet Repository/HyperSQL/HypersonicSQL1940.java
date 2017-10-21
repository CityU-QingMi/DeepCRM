    public int add(long key, long value) {

        if (key > Integer.MAX_VALUE || key < Integer.MIN_VALUE) {
            throw new IllegalArgumentException();
        }

        if (value > Integer.MAX_VALUE || value < Integer.MIN_VALUE) {
            throw new IllegalArgumentException();
        }

        return add((int) key, (int) value);
    }
