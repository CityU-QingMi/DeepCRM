    public int lookup(int key) throws NoSuchElementException {

        if (sortOnValues) {
            sorted       = false;
            sortOnValues = false;
        }

        int i = findFirstEqualKeyIndex(key);

        if (i == -1) {
            throw new NoSuchElementException();
        }

        return getValue(i);
    }
