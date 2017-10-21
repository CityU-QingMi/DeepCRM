    private static LoggerContext locateContext(final Bundle bundle, final URI configLocation) {
        final String name = Objects.requireNonNull(bundle, "No Bundle provided").getSymbolicName();
        final AtomicReference<WeakReference<LoggerContext>> ref = CONTEXT_MAP.get(name);
        if (ref == null) {
            final LoggerContext context = new LoggerContext(name, bundle, configLocation);
            CONTEXT_MAP.putIfAbsent(name,
                new AtomicReference<>(new WeakReference<>(context)));
            return CONTEXT_MAP.get(name).get().get();
        }
        final WeakReference<LoggerContext> r = ref.get();
        final LoggerContext ctx = r.get();
        if (ctx == null) {
            final LoggerContext context = new LoggerContext(name, bundle, configLocation);
            ref.compareAndSet(r, new WeakReference<>(context));
            return ref.get().get();
        }
        final URI oldConfigLocation = ctx.getConfigLocation();
        if (oldConfigLocation == null && configLocation != null) {
            LOGGER.debug("Setting bundle ({}) configuration to {}", name, configLocation);
            ctx.setConfigLocation(configLocation);
        } else if (oldConfigLocation != null && configLocation != null && !configLocation.equals(oldConfigLocation)) {
            LOGGER.warn("locateContext called with URI [{}], but existing LoggerContext has URI [{}]",
                configLocation, oldConfigLocation);
        }
        return ctx;
    }
