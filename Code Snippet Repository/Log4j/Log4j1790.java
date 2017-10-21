    public void tearDown() throws SQLException {
        final LoggerContext context = LoggerContext.getContext(false);
        try {
            String appenderName = "databaseAppender";
            final Appender appender = context.getConfiguration().getAppender(appenderName);
            assertNotNull("The appender '" + appenderName + "' should not be null.", appender);
            assertTrue("The appender should be a JpaAppender.", appender instanceof JpaAppender);
            ((JpaAppender) appender).getManager().close();
        } finally {
            System.clearProperty(ConfigurationFactory.CONFIGURATION_FILE_PROPERTY);
            context.reconfigure();
            StatusLogger.getLogger().reset();

            try (Statement statement = this.connection.createStatement();) {
                statement.execute("SHUTDOWN");
            }

            this.connection.close();
        }
    }
