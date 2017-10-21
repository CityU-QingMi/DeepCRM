    public ConfigurationBuilder<BuiltConfiguration> buildConfigurationBuilder(final InputStream input)
            throws IOException {
        try {
            properties.load(input);
            strSubstitutorProperties = new StrSubstitutor(properties);
            strSubstitutorSystem = new StrSubstitutor(System.getProperties());
            final String rootCategoryValue = getLog4jValue(ROOTCATEGORY);
            final String rootLoggerValue = getLog4jValue(ROOTLOGGER);
            if (rootCategoryValue == null && rootLoggerValue == null) {
                // This is not a Log4j 1 properties configuration file.
                warn("Missing " + ROOTCATEGORY + " or " + ROOTLOGGER + " in " + input);
                // throw new ConfigurationException(
                // "Missing " + ROOTCATEGORY + " or " + ROOTLOGGER + " in " + input);
            }
            builder.setConfigurationName("Log4j1");
            // DEBUG
            final String debugValue = getLog4jValue("debug");
            if (Boolean.valueOf(debugValue)) {
                builder.setStatusLevel(Level.DEBUG);
            }
            // Root
            buildRootLogger(getLog4jValue(ROOTCATEGORY));
            buildRootLogger(getLog4jValue(ROOTLOGGER));
            // Appenders
            final Map<String, String> appenderNameToClassName = buildClassToPropertyPrefixMap();
            for (final Map.Entry<String, String> entry : appenderNameToClassName.entrySet()) {
                final String appenderName = entry.getKey();
                final String appenderClass = entry.getValue();
                buildAppender(appenderName, appenderClass);
            }
            // Loggers
            buildLoggers("log4j.category.");
            buildLoggers("log4j.logger.");
            buildProperties();
            return builder;
        } catch (final IllegalArgumentException e) {
            throw new ConfigurationException(e);
        }
    }
