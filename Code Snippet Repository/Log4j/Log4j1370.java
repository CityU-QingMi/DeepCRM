    @Override
    public LoggerContext getContext(final String fqcn, final ClassLoader loader, final boolean currentContext,
                                    final URI configLocation) {
        if (currentContext) {
            final LoggerContext ctx = ContextAnchor.THREAD_CONTEXT.get();
            if (ctx != null) {
                return ctx;
            }
            return getDefault();
        }
        // it's quite possible that the provided ClassLoader may implement BundleReference which gives us a nice shortcut
        if (loader instanceof BundleReference) {
            return locateContext(((BundleReference) loader).getBundle(), configLocation);
        }
        final Class<?> callerClass = StackLocatorUtil.getCallerClass(fqcn);
        if (callerClass != null) {
            return locateContext(FrameworkUtil.getBundle(callerClass), configLocation);
        }
        final LoggerContext lc = ContextAnchor.THREAD_CONTEXT.get();
        return lc == null ? getDefault() : lc;
    }
