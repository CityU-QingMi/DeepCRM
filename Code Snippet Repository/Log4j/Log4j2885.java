    private V removeNullEntry() {
        containsNullKey = false;
        keys[arraySize] = null;
        final V oldValue = values[arraySize];
        values[arraySize] = null;
        size--;
        if (size < maxFill / 4 && arraySize > DEFAULT_INITIAL_SIZE) {
            rehash(arraySize / 2);
        }
        return oldValue;
    }
