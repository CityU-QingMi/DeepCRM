    @PerformanceSensitive
    public Class<?> getCallerClass(final Class<?> anchor) {
        boolean next = false;
        Class<?> clazz;
        for (int i = 2; null != (clazz = getCallerClass(i)); i++) {
            if (anchor.equals(clazz)) {
                next = true;
                continue;
            }
            if (next) {
                return clazz;
            }
        }
        return Object.class;
    }
