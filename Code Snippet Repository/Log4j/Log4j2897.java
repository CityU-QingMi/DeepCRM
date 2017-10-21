    @SuppressWarnings("")
    private boolean containsObjectKey(final Object k) {
        if (k == null) {
            return containsNullKey;
        }
        K curr;
        final K[] key = this.keys;
        int pos;
        // The starting point.
        if ((curr = key[pos = HashCommon.mix(k.hashCode()) & mask]) == null) {
            return false;
        }
        if (k.equals(curr)) {
            return true;
        }
        // There's always an unused entry.
        while (true) {
            if ((curr = key[pos = (pos + 1) & mask]) == null) {
                return false;
            }
            if (k.equals(curr)) {
                return true;
            }
        }
    }
