    public static StringMap createContextData() {
        if (DEFAULT_CONSTRUCTOR == null) {
            return new SortedArrayStringMap();
        }
        try {
            return (StringMap) DEFAULT_CONSTRUCTOR.invoke();
        } catch (final Throwable ignored) {
            return new SortedArrayStringMap();
        }
    }
