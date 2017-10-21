    @PluginFactory
    public static NoSqlAppender createAppender(
            @PluginAttribute("name") final String name,
            @PluginAttribute("ignoreExceptions") final String ignore,
            @PluginElement("Filter") final Filter filter,
            @PluginAttribute("bufferSize") final String bufferSize,
            @PluginElement("NoSqlProvider") final NoSqlProvider<?> provider) {
        if (provider == null) {
            LOGGER.error("NoSQL provider not specified for appender [{}].", name);
            return null;
        }

        final int bufferSizeInt = AbstractAppender.parseInt(bufferSize, 0);
        final boolean ignoreExceptions = Booleans.parseBoolean(ignore, true);

        final String managerName = "noSqlManager{ description=" + name + ", bufferSize=" + bufferSizeInt
                + ", provider=" + provider + " }";

        final NoSqlDatabaseManager<?> manager = NoSqlDatabaseManager.getNoSqlDatabaseManager(
                managerName, bufferSizeInt, provider
        );
        if (manager == null) {
            return null;
        }

        return new NoSqlAppender(name, filter, ignoreExceptions, manager);
    }
