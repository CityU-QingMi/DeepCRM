    @SuppressWarnings("")
    private V getObjectValue(final Object k) {
        if (k == null) {
            return containsNullKey ? values[arraySize] : defRetValue;
        }
        K curr;
        final K[] key = this.keys;
        int pos;
        // The starting point.
        if ((curr = key[pos = HashCommon.mix(k.hashCode()) & mask]) == null) {
            return defRetValue;
        }
        if (k.equals(curr)) {
            return values[pos];
        }
        // There's always an unused entry.
        while (true) {
            if (((curr = key[pos = (pos + 1) & mask]) == null)) {
                return defRetValue;
            }
            if (k.equals(curr)) {
                return values[pos];
            }
        }
    }
