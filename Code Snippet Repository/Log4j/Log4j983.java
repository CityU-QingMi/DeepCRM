    public CompositeConfiguration(final List<? extends AbstractConfiguration> configurations) {
        super(configurations.get(0).getLoggerContext(), ConfigurationSource.NULL_SOURCE);
        rootNode = configurations.get(0).getRootNode();
        this.configurations = configurations;
        final String mergeStrategyClassName = PropertiesUtil.getProperties().getStringProperty(MERGE_STRATEGY_PROPERTY,
                DefaultMergeStrategy.class.getName());
        try {
            mergeStrategy = LoaderUtil.newInstanceOf(mergeStrategyClassName);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException |
                InstantiationException ex) {
            mergeStrategy = new DefaultMergeStrategy();
        }
        for (final AbstractConfiguration config : configurations) {
            mergeStrategy.mergeRootProperties(rootNode, config);
        }
        final StatusConfiguration statusConfig = new StatusConfiguration().withVerboseClasses(VERBOSE_CLASSES)
                .withStatus(getDefaultStatus());
        for (final Map.Entry<String, String> entry : rootNode.getAttributes().entrySet()) {
            final String key = entry.getKey();
            final String value = getStrSubstitutor().replace(entry.getValue());
            if ("status".equalsIgnoreCase(key)) {
                statusConfig.withStatus(value.toUpperCase());
            } else if ("dest".equalsIgnoreCase(key)) {
                statusConfig.withDestination(value);
            } else if ("shutdownHook".equalsIgnoreCase(key)) {
                isShutdownHookEnabled = !"disable".equalsIgnoreCase(value);
            } else if ("shutdownTimeout".equalsIgnoreCase(key)) {
                shutdownTimeoutMillis = Long.parseLong(value);
            } else if ("verbose".equalsIgnoreCase(key)) {
                statusConfig.withVerbosity(value);
            } else if ("packages".equalsIgnoreCase(key)) {
                pluginPackages.addAll(Arrays.asList(value.split(Patterns.COMMA_SEPARATOR)));
            } else if ("name".equalsIgnoreCase(key)) {
                setName(value);
            }
        }
        statusConfig.initialize();
    }
