    public static StringMap createContextData(final int initialCapacity) {
        if (INITIAL_CAPACITY_CONSTRUCTOR == null) {
            return new SortedArrayStringMap(initialCapacity);
        }
        try {
            return (StringMap) INITIAL_CAPACITY_CONSTRUCTOR.invoke(initialCapacity);
        } catch (final Throwable ignored) {
            return new SortedArrayStringMap(initialCapacity);
        }
    }
