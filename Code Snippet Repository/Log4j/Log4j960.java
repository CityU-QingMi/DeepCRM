    protected LoggerConfig(final String name, final List<AppenderRef> appenders, final Filter filter,
            final Level level, final boolean additive, final Property[] properties, final Configuration config,
            final boolean includeLocation) {
        super(filter);
        this.logEventFactory = LOG_EVENT_FACTORY;
        this.name = name;
        this.appenderRefs = appenders;
        this.level = level;
        this.additive = additive;
        this.includeLocation = includeLocation;
        this.config = config;
        if (properties != null && properties.length > 0) {
            this.properties = Collections.unmodifiableList(Arrays.asList(Arrays.copyOf(
                    properties, properties.length)));
        } else {
            this.properties = null;
        }
        this.propertiesRequireLookup = containsPropertyRequiringLookup(properties);
        this.reliabilityStrategy = config.getReliabilityStrategy(this);
    }
