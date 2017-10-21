    public C3p0PoolingConnectionProvider(Properties config) throws SchedulerException, SQLException {
        PropertiesParser cfg = new PropertiesParser(config);
        initialize(
                cfg.getStringProperty(DB_DRIVER),
                cfg.getStringProperty(DB_URL),
                cfg.getStringProperty(DB_USER, ""),
                cfg.getStringProperty(DB_PASSWORD, ""),
                cfg.getIntProperty(DB_MAX_CONNECTIONS, DEFAULT_DB_MAX_CONNECTIONS),
                cfg.getIntProperty(DB_MAX_CACHED_STATEMENTS_PER_CONNECTION, DEFAULT_DB_MAX_CACHED_STATEMENTS_PER_CONNECTION),
                cfg.getStringProperty(DB_VALIDATION_QUERY),
                cfg.getBooleanProperty(DB_VALIDATE_ON_CHECKOUT, false),
                cfg.getIntProperty(DB_IDLE_VALIDATION_SECONDS, 50),
                cfg.getIntProperty(DB_DISCARD_IDLE_CONNECTIONS_SECONDS, 0));
    }
