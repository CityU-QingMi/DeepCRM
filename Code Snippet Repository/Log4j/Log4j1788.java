    public JdbcAppenderHsqldbFactoryMethodTest() {
        super(new JdbcRule(
                new ConnectionSource() {
                    @Override
                    public Connection getConnection() throws SQLException {
                        return JdbcAppenderHsqldbFactoryMethodTest.getConnection();
                    }
                },
                "CREATE TABLE fmLogEntry (" +
                    "id INTEGER IDENTITY, eventDate DATETIME, literalColumn VARCHAR(255), level VARCHAR(10), " +
                    "logger VARCHAR(255), message VARCHAR(1024), exception CLOB, anotherDate TIMESTAMP" +
                    ")",
                "DROP TABLE fmLogEntry"
            ),
            "hsqldb"
        );
    }
