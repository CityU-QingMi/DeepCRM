    public HikariCpPoolingConnectionProvider(Properties config) throws SchedulerException, SQLException {
        PropertiesParser cfg = new PropertiesParser(config);
        initialize(
                cfg.getStringProperty(DB_DRIVER),
                cfg.getStringProperty(DB_URL),
                cfg.getStringProperty(DB_USER, ""),
                cfg.getStringProperty(DB_PASSWORD, ""),
                cfg.getIntProperty(DB_MAX_CONNECTIONS, DEFAULT_DB_MAX_CONNECTIONS),
                cfg.getStringProperty(DB_VALIDATION_QUERY),
                cfg.getIntProperty(DB_DISCARD_IDLE_CONNECTIONS_SECONDS, 0));
    }
