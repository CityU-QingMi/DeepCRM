    public static ContextDataInjector createInjector() {
        final String className = PropertiesUtil.getProperties().getStringProperty("log4j2.ContextDataInjector");
        if (className == null) {
            return createDefaultInjector();
        }
        try {
            final Class<? extends ContextDataInjector> cls = LoaderUtil.loadClass(className).asSubclass(
                    ContextDataInjector.class);
            return cls.newInstance();
        } catch (final Exception dynamicFailed) {
            final ContextDataInjector result = createDefaultInjector();
            StatusLogger.getLogger().warn(
                    "Could not create ContextDataInjector for '{}', using default {}: {}",
                    className, result.getClass().getName(), dynamicFailed);
            return result;
        }
    }
