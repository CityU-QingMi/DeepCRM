    public JdbcAppenderHsqldbDataSourceTest() {
        super(new JdbcRule(
            new ConnectionSource() {
                @Override
                public Connection getConnection() throws SQLException {
                    return DriverManager.getConnection("jdbc:hsqldb:mem:Log4j", "sa", "");
                }
            },
            "CREATE TABLE dsLogEntry (" +
                "id INTEGER IDENTITY, eventDate DATETIME, literalColumn VARCHAR(255), level VARCHAR(10), " +
                "logger VARCHAR(255), message VARCHAR(1024), exception CLOB, anotherDate TIMESTAMP" +
                ")",
            "DROP TABLE dsLogEntry"
        ));
    }
