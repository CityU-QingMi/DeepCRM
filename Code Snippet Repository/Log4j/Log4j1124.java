    private static MethodHandle createInitialCapacityConstructor(final Class<? extends StringMap> cachedClass) {
        if (cachedClass == null) {
            return null;
        }
        try {
            return LOOKUP.findConstructor(cachedClass, MethodType.methodType(void.class, int.class));
        } catch (final NoSuchMethodException | IllegalAccessException ignored) {
            return null;
        }
    }
