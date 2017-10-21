    public int get(long key) throws NoSuchElementException {

        int lookup = getLookup(key);

        if (lookup != -1) {
            return intValueTable[lookup];
        }

        throw new NoSuchElementException();
    }
