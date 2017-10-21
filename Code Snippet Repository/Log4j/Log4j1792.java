    @Override
    protected Connection setUpConnection() throws SQLException {
        final Connection connection = DriverManager.getConnection("jdbc:hsqldb:mem:Log4j", USER_ID, PASSWORD);

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE jpaBaseLogEntry ( "
                    + "id INTEGER IDENTITY, eventDate DATETIME, level VARCHAR(10), logger VARCHAR(255), "
                    + "message VARCHAR(1024), exception VARCHAR(1048576)" + " )");
        }

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE jpaBasicLogEntry ( "
                    + "id INTEGER IDENTITY, timemillis BIGINT, nanoTime BIGINT, level VARCHAR(10), loggerName VARCHAR(255), "
                    + "message VARCHAR(1024), thrown VARCHAR(1048576), contextMapJson VARCHAR(1048576),"
                    + "loggerFQCN VARCHAR(1024), contextStack VARCHAR(1048576), marker VARCHAR(255), source VARCHAR(2048),"
                    + "threadId BIGINT, threadName NVARCHAR(255), threadPriority INTEGER" + " )");
        }

        return connection;
    }
