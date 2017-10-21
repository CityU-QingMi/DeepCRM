    @Override
    public int indexOfKey(final String key) {
        if (keys == EMPTY) {
            return -1;
        }
        if (key == null) { // null key is located at the start of the array
            return nullKeyIndex(); // insert at index zero
        }
        final int start = size > 0 && keys[0] == null ? 1 : 0;
        return Arrays.binarySearch(keys, start, size, key);
    }
