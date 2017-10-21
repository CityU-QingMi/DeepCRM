        public BasicConfiguration(final LoggerContext loggerContext) {
            super(loggerContext, ConfigurationSource.NULL_SOURCE);

            final LoggerConfig root = getRootLogger();
            setName("BasicConfiguration");
            final String levelName = System.getProperty(DEFAULT_LEVEL);
            final Level level = (levelName != null && Level.getLevel(levelName) != null) ? Level.getLevel(levelName)
                    : Level.DEBUG;
            root.setLevel(level);
        }
