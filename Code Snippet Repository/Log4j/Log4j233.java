    private void resize(final int newCapacity) {
        final String[] oldKeys = keys;
        final Object[] oldValues = values;

        keys = new String[newCapacity];
        values = new Object[newCapacity];

        System.arraycopy(oldKeys, 0, keys, 0, size);
        System.arraycopy(oldValues, 0, values, 0, size);

        threshold = newCapacity;
    }
