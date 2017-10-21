    @SuppressWarnings("")
    @Override
    public <V> void forEach(final BiConsumer<String, ? super V> action) {
        iterating = true;
        try {
            for (int i = 0; i < size; i++) {
                action.accept(keys[i], (V) values[i]);
            }
        } finally {
            iterating = false;
        }
    }
