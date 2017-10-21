    @SuppressWarnings("")
    private void readObject(final ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        arraySize = HashCommon.arraySize(size, loadFactor);
        maxFill = HashCommon.maxFill(arraySize, loadFactor);
        mask = arraySize - 1;
        final K key[] = this.keys = (K[]) new Object[arraySize + 1];
        final V value[] = this.values = (V[]) new Object[arraySize + 1];
        K k;
        V v;
        for (int i = size, pos; i-- != 0;) {
            k = (K) s.readObject();
            v = (V) s.readObject();
            if (k == null) {
                pos = arraySize;
                containsNullKey = true;
            } else {
                pos = HashCommon.mix(k.hashCode()) & mask;
                while (key[pos] != null) {
                    pos = (pos + 1) & mask;
                }
            }
            key[pos] = k;
            value[pos] = v;
        }
    }
