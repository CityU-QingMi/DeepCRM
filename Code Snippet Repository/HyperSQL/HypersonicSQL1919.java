    public int lookup(int key, int def) {

        if (sortOnValues) {
            sorted       = false;
            sortOnValues = false;
        }

        int i = findFirstEqualKeyIndex(key);

        if (i == -1) {
            return def;
        }

        return getValue(i);
    }
