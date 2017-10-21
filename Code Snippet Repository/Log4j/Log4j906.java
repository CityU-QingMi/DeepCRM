    protected AbstractConfiguration(final LoggerContext loggerContext, final ConfigurationSource configurationSource) {
        this.loggerContext = new WeakReference<>(loggerContext);
        // The loggerContext is null for the NullConfiguration class.
        // this.loggerContext = new WeakReference(Objects.requireNonNull(loggerContext, "loggerContext is null"));
        this.configurationSource = Objects.requireNonNull(configurationSource, "configurationSource is null");
        componentMap.put(Configuration.CONTEXT_PROPERTIES, properties);
        pluginManager = new PluginManager(Node.CATEGORY);
        rootNode = new Node();
        setState(State.INITIALIZING);

    }
