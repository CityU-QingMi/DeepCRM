    @Override
    @SuppressWarnings("")
    public <VAL> void forEach(final BiConsumer<String, ? super VAL> action) {
        final int startSize = size;
        final K myKeys[] = this.keys;
        int pos = arraySize;

        iterating = true;
        try {
            if (containsNullKey) {
                action.accept((String) myKeys[pos], (VAL) values[pos]);
                if (size != startSize) {
                    throw new ConcurrentModificationException();
                }
            }
            --pos;
            for (; pos >= 0; pos--) {
                if (myKeys[pos] != null) {
                    action.accept((String) myKeys[pos], (VAL) values[pos]);
                    if (size != startSize) {
                        throw new ConcurrentModificationException();
                    }
                }
            }
        } finally {
            iterating = false;
        }
    }
