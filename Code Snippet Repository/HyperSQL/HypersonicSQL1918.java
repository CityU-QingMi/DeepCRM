    public long lookup(long key, long def) {

        if (key > Integer.MAX_VALUE || key < Integer.MIN_VALUE) {
            return def;
        }

        if (sortOnValues) {
            sorted       = false;
            sortOnValues = false;
        }

        int i = findFirstEqualKeyIndex((int) key);

        if (i == -1) {
            return def;
        }

        return getValue(i);
    }
