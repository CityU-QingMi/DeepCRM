    @SuppressWarnings("")
    private V removeObjectKey(final Object k) {
        assertNotFrozen();
        assertNoConcurrentModification();

        if (k == null) {
            if (containsNullKey) {
                return removeNullEntry();
            }
            return defRetValue;
        }
        final K[] key = this.keys;
        int pos = HashCommon.mix(k.hashCode()) & mask;
        K curr = key[pos & mask];
        // The starting point.
        if (curr == null) {
            return defRetValue;
        }
        if (k.equals(curr)) {
            return removeEntry(pos);
        }
        while (true) {
            if ((curr = key[pos = (pos + 1) & mask]) == null) {
                return defRetValue;
            }
            if (k.equals(curr)) {
                return removeEntry(pos);
            }
        }
    }
