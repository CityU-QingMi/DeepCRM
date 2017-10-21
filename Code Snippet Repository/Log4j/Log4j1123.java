    private static MethodHandle createDefaultConstructor(final Class<? extends StringMap> cachedClass) {
        if (cachedClass == null) {
            return null;
        }
        try {
            return LOOKUP.findConstructor(cachedClass, MethodType.methodType(void.class));
        } catch (final NoSuchMethodException | IllegalAccessException ignored) {
            return null;
        }
    }
