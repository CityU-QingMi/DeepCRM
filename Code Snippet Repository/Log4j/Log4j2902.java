    private int insert(final K k, final V v) {
        int pos;
        if (k == null) {
            if (containsNullKey) {
                return arraySize;
            }
            containsNullKey = true;
            pos = arraySize;
        } else {
            K curr;
            final K[] key = this.keys;
            // The starting point.
            if (!((curr = key[pos = HashCommon.mix(k.hashCode()) & mask]) == null)) {
                if (curr.equals(k)) {
                    return pos;
                }
                while (!((curr = key[pos = (pos + 1) & mask]) == null)) {
                    if (curr.equals(k)) {
                        return pos;
                    }
                }
            }
        }
        keys[pos] = k;
        values[pos] = v;
        if (size++ >= maxFill) {
            rehash(HashCommon.arraySize(size + 1, loadFactor));
        }
        return -1;
    }
