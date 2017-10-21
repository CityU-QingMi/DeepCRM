    public static ConfigurationFactory getInstance() {
        // volatile works in Java 1.6+, so double-checked locking also works properly
        //noinspection DoubleCheckedLocking
        if (factories == null) {
            LOCK.lock();
            try {
                if (factories == null) {
                    final List<ConfigurationFactory> list = new ArrayList<>();
                    final String factoryClass = PropertiesUtil.getProperties().getStringProperty(CONFIGURATION_FACTORY_PROPERTY);
                    if (factoryClass != null) {
                        addFactory(list, factoryClass);
                    }
                    final PluginManager manager = new PluginManager(CATEGORY);
                    manager.collectPlugins();
                    final Map<String, PluginType<?>> plugins = manager.getPlugins();
                    final List<Class<? extends ConfigurationFactory>> ordered = new ArrayList<>(plugins.size());
                    for (final PluginType<?> type : plugins.values()) {
                        try {
                            ordered.add(type.getPluginClass().asSubclass(ConfigurationFactory.class));
                        } catch (final Exception ex) {
                            LOGGER.warn("Unable to add class {}", type.getPluginClass(), ex);
                        }
                    }
                    Collections.sort(ordered, OrderComparator.getInstance());
                    for (final Class<? extends ConfigurationFactory> clazz : ordered) {
                        addFactory(list, clazz);
                    }
                    // see above comments about double-checked locking
                    //noinspection NonThreadSafeLazyInitialization
                    factories = Collections.unmodifiableList(list);
                }
            } finally {
                LOCK.unlock();
            }
        }

        LOGGER.debug("Using configurationFactory {}", configFactory);
        return configFactory;
    }
