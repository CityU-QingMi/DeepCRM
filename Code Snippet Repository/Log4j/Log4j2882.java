    private V putObjectValue(final K k, final V v) {
        assertNotFrozen();
        assertNoConcurrentModification();

        final int pos = insert(k, v);
        if (pos < 0) {
            return defRetValue;
        }
        final V oldValue = values[pos];
        values[pos] = v;
        return oldValue;
    }
