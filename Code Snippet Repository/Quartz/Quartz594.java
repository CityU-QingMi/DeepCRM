    private void initialize(
            String dbDriver,
            String dbURL,
            String dbUser,
            String dbPassword,
            int maxConnections,
            String dbValidationQuery,
            int maxIdleSeconds) throws SQLException, SchedulerException {
        if (dbURL == null) {
            throw new SQLException(
                    "DBPool could not be created: DB URL cannot be null");
        }

        if (dbDriver == null) {
            throw new SQLException(
                    "DBPool '" + dbURL + "' could not be created: " +
                            "DB driver class name cannot be null!");
        }

        if (maxConnections < 0) {
            throw new SQLException(
                    "DBPool '" + dbURL + "' could not be created: " +
                            "Max connections must be greater than zero!");
        }


        datasource = new HikariDataSource();
        datasource.setDriverClassName(dbDriver);
        datasource.setJdbcUrl(dbURL);
        datasource.setUsername(dbUser);
        datasource.setPassword(dbPassword);
        datasource.setMaximumPoolSize(maxConnections);
        datasource.setIdleTimeout(maxIdleSeconds);

        if (dbValidationQuery != null) {
            datasource.setConnectionTestQuery(dbValidationQuery);
        }
    }
