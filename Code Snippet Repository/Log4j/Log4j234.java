    @Override
    public void remove(final String key) {
        if (keys == EMPTY) {
            return;
        }

        final int index = indexOfKey(key);
        if (index >= 0) {
            assertNotFrozen();
            assertNoConcurrentModification();

            System.arraycopy(keys, index + 1, keys, index, size - 1 - index);
            System.arraycopy(values, index + 1, values, index, size - 1 - index);
            keys[size - 1] = null;
            values[size - 1] = null;
            size--;
        }
    }
