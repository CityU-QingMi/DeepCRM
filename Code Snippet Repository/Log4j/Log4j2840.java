    @TearDown
    public void tearDown() throws SQLException {
        final LoggerContext context = LoggerContext.getContext(false);
        try {
            ((JpaAppender) context.getConfiguration().getAppender("H2Appender")).getManager().close();
            ((JpaAppender) context.getConfiguration().getAppender("HSQLDBAppender")).getManager().close();
        } finally {
            System.clearProperty(ConfigurationFactory.CONFIGURATION_FILE_PROPERTY);
            // context.reconfigure();
            // StatusLogger.getLogger().reset();

            Statement statement = null;
            try {
                statement = connectionHSQLDB.createStatement();
                statement.execute("SHUTDOWN");
            } catch (final SQLException ignore) {
                // ignore
            } finally {
                Closer.closeSilently(statement);
                Closer.closeSilently(connectionHSQLDB);
            }
            try {
                statement = connectionH2.createStatement();
                statement.execute("SHUTDOWN");
            } catch (final SQLException ignore) {
                // ignore
            } finally {
                Closer.closeSilently(statement);
                Closer.closeSilently(connectionH2);
            }
        }
    }
