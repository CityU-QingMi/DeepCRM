    @Override
    public void putValue(final String key, final Object value) {
        assertNotFrozen();
        assertNoConcurrentModification();

        if (keys == EMPTY) {
            inflateTable(threshold);
        }
        final int index = indexOfKey(key);
        if (index >= 0) {
            keys[index] = key;
            values[index] = value;
        } else { // not found, so insert.
            insertAt(~index, key, value);
        }
    }
