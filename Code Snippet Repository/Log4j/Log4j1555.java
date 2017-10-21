    public static <T> Constructor<T> getDefaultConstructor(final Class<T> clazz) {
        Objects.requireNonNull(clazz, "No class provided");
        try {
            final Constructor<T> constructor = clazz.getDeclaredConstructor();
            makeAccessible(constructor);
            return constructor;
        } catch (final NoSuchMethodException ignored) {
            try {
                final Constructor<T> constructor = clazz.getConstructor();
                makeAccessible(constructor);
                return constructor;
            } catch (final NoSuchMethodException e) {
                throw new IllegalStateException(e);
            }
        }
    }
