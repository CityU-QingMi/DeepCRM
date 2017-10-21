    public Interpolator(final Map<String, String> properties) {
        this.defaultLookup = new MapLookup(properties == null ? new HashMap<String, String>() : properties);
        // TODO: this ought to use the PluginManager
        lookups.put("log4j", new Log4jLookup());
        lookups.put("sys", new SystemPropertiesLookup());
        lookups.put("env", new EnvironmentLookup());
        lookups.put("main", MainMapLookup.MAIN_SINGLETON);
        lookups.put("marker", new MarkerLookup());
        lookups.put("java", new JavaLookup());
        // JNDI
        try {
            // [LOG4J2-703] We might be on Android
            lookups.put(LOOKUP_KEY_JNDI,
                Loader.newCheckedInstanceOf("org.apache.logging.log4j.core.lookup.JndiLookup", StrLookup.class));
        } catch (final LinkageError | Exception e) {
            handleError(LOOKUP_KEY_JNDI, e);
        }
        // JMX input args
        try {
            // We might be on Android
            lookups.put(LOOKUP_KEY_JVMRUNARGS,
                Loader.newCheckedInstanceOf("org.apache.logging.log4j.core.lookup.JmxRuntimeInputArgumentsLookup",
                        StrLookup.class));
        } catch (final LinkageError | Exception e) {
            handleError(LOOKUP_KEY_JVMRUNARGS, e);
        }
        lookups.put("date", new DateLookup());
        lookups.put("ctx", new ContextMapLookup());
        if (Loader.isClassAvailable("javax.servlet.ServletContext")) {
            try {
                lookups.put(LOOKUP_KEY_WEB,
                    Loader.newCheckedInstanceOf("org.apache.logging.log4j.web.WebLookup", StrLookup.class));
            } catch (final Exception ignored) {
                handleError(LOOKUP_KEY_WEB, ignored);
            }
        } else {
            LOGGER.debug("Not in a ServletContext environment, thus not loading WebLookup plugin.");
        }
    }
