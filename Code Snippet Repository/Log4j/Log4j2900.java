    @Override
    @SuppressWarnings("")
    public <VAL, STATE> void forEach(final TriConsumer<String, ? super VAL, STATE> action, final STATE state) {
        final int startSize = size;
        final K myKeys[] = this.keys;
        int pos = arraySize;

        iterating = true;
        try {
            if (containsNullKey) {
                action.accept((String) myKeys[pos], (VAL) values[pos], state);
                if (size != startSize) {
                    throw new ConcurrentModificationException();
                }
            }
            --pos;
            for (; pos >= 0; pos--) {
                if (myKeys[pos] != null) {
                    action.accept((String) myKeys[pos], (VAL) values[pos], state);
                    if (size != startSize) {
                        throw new ConcurrentModificationException();
                    }
                }
            }
        } finally {
            iterating = false;
        }
    }
