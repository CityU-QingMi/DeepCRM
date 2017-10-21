    @SuppressWarnings("")
    protected void rehash(final int newN) {
        final K myKeys[] = this.keys;
        final V myValues[] = this.values;
        final int mask = newN - 1; // Note that this is used by the hashing
        // macro
        final K newKey[] = (K[]) new Object[newN + 1];
        final V newValue[] = (V[]) new Object[newN + 1];
        int i = arraySize, pos;
        for (int j = realSize(); j-- != 0;) {
            while (myKeys[--i] == null) {
                // advance i until we find an existing key
            }
            if (newKey[pos = HashCommon.mix(myKeys[i].hashCode()) & mask] != null) { // rehash & check slot availability
                while (newKey[pos = (pos + 1) & mask] != null) {
                    // find available slot at (or immediately following) pos
                }
            }
            newKey[pos] = myKeys[i];
            newValue[pos] = myValues[i];
        }
        newValue[newN] = myValues[arraySize];
        arraySize = newN;
        this.mask = mask;
        maxFill = HashCommon.maxFill(arraySize, loadFactor);
        this.keys = newKey;
        this.values = newValue;
    }
