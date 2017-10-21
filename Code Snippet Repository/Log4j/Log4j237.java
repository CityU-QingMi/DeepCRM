    @PerformanceSensitive
    public Class<?> getCallerClass(final String fqcn, final String pkg) {
        boolean next = false;
        Class<?> clazz;
        for (int i = 2; null != (clazz = getCallerClass(i)); i++) {
            if (fqcn.equals(clazz.getName())) {
                next = true;
                continue;
            }
            if (next && clazz.getName().startsWith(pkg)) {
                return clazz;
            }
        }
        // TODO: return Object.class
        return null;
    }
