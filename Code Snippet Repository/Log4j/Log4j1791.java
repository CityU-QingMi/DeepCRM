    @Override
    protected Connection setUpConnection() throws SQLException {
        final Connection connection = DriverManager.getConnection("jdbc:h2:mem:Log4j", USER_ID, PASSWORD);

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE jpaBaseLogEntry ( "
                    + "id INTEGER IDENTITY, eventDate DATETIME, level NVARCHAR(10), logger NVARCHAR(255), "
                    + "message NVARCHAR(1024), exception NVARCHAR(1048576)" + " )");
        }

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE jpaBasicLogEntry ( "
                    + "id INTEGER IDENTITY, timemillis BIGINT, nanoTime BIGINT, level NVARCHAR(10), loggerName NVARCHAR(255), "
                    + "message NVARCHAR(1024), thrown NVARCHAR(1048576), contextMapJson NVARCHAR(1048576),"
                    + "loggerFQCN NVARCHAR(1024), contextStack NVARCHAR(1048576), marker NVARCHAR(255), source NVARCHAR(2048),"
                    + "threadId BIGINT, threadName NVARCHAR(255), threadPriority INTEGER" + " )");
        }

        return connection;
    }
