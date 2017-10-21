    @PerformanceSensitive
    public Stack<Class<?>> getCurrentStackTrace() {
        // benchmarks show that using the SecurityManager is much faster than looping through getCallerClass(int)
        if (getSecurityManager() != null) {
            final Class<?>[] array = getSecurityManager().getClassContext();
            final Stack<Class<?>> classes = new Stack<>();
            classes.ensureCapacity(array.length);
            for (final Class<?> clazz : array) {
                classes.push(clazz);
            }
            return classes;
        }
        // slower version using getCallerClass where we cannot use a SecurityManager
        final Stack<Class<?>> classes = new Stack<>();
        Class<?> clazz;
        for (int i = 1; null != (clazz = getCallerClass(i)); i++) {
            classes.push(clazz);
        }
        return classes;
    }
