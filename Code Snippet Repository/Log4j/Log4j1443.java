    @Override
    public LoggerContext getContext(final String fqcn, final ClassLoader loader, final boolean currentContext,
            final URI configLocation) {
        if (currentContext) {
            final LoggerContext ctx = ContextAnchor.THREAD_CONTEXT.get();
            if (ctx != null) {
                return ctx;
            }
            return getDefault();
        } else if (loader != null) {
            return locateContext(loader, configLocation);
        } else {
            final Class<?> clazz = StackLocatorUtil.getCallerClass(fqcn);
            if (clazz != null) {
                return locateContext(clazz.getClassLoader(), configLocation);
            }
            final LoggerContext lc = ContextAnchor.THREAD_CONTEXT.get();
            if (lc != null) {
                return lc;
            }
            return getDefault();
        }
    }
