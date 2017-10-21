    @Override
    public void initialize() {
        LOGGER.debug("Initializing configuration {}", this);
        subst.setConfiguration(this);
        try {
            scriptManager = new ScriptManager(this, watchManager);
        } catch (final LinkageError | Exception e) {
            // LOG4J2-1920 ScriptEngineManager is not available in Android
            LOGGER.info("Cannot initialize scripting support because this JRE does not support it.", e);
        }
        pluginManager.collectPlugins(pluginPackages);
        final PluginManager levelPlugins = new PluginManager(Level.CATEGORY);
        levelPlugins.collectPlugins(pluginPackages);
        final Map<String, PluginType<?>> plugins = levelPlugins.getPlugins();
        if (plugins != null) {
            for (final PluginType<?> type : plugins.values()) {
                try {
                    // Cause the class to be initialized if it isn't already.
                    Loader.initializeClass(type.getPluginClass().getName(), type.getPluginClass().getClassLoader());
                } catch (final Exception e) {
                    LOGGER.error("Unable to initialize {} due to {}", type.getPluginClass().getName(), e.getClass()
                            .getSimpleName(), e);
                }
            }
        }
        setup();
        setupAdvertisement();
        doConfigure();
        setState(State.INITIALIZED);
        LOGGER.debug("Configuration {} initialized", this);
    }
