    @SuppressWarnings("")
    @Override
    public <V, T> void forEach(final TriConsumer<String, ? super V, T> action, final T state) {
        iterating = true;
        try {
            for (int i = 0; i < size; i++) {
                action.accept(keys[i], (V) values[i], state);
            }
        } finally {
            iterating = false;
        }
    }
