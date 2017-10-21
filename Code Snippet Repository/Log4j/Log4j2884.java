    private V removeEntry(final int pos) {
        final V oldValue = values[pos];
        values[pos] = null;
        size--;
        shiftKeys(pos);
        if (size < maxFill / 4 && arraySize > DEFAULT_INITIAL_SIZE) {
            rehash(arraySize / 2);
        }
        return oldValue;
    }
