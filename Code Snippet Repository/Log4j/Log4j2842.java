    public static Connection getConnectionHSQLDB() throws Exception {
        Class.forName("org.hsqldb.jdbcDriver");
        final Connection connection = DriverManager.getConnection("jdbc:hsqldb:mem:Log4j", "sa", "");
        final Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE jpaBasicLogEntry ( "
                + "id INTEGER IDENTITY, timemillis BIGINT, level VARCHAR(10), loggerName VARCHAR(255), "
                + "message VARCHAR(1024), thrown VARCHAR(1048576), contextMapJson VARCHAR(1048576),"
                + "loggerFQCN VARCHAR(1024), contextStack VARCHAR(1048576), marker VARCHAR(255), source VARCHAR(2048),"
                + "threadName VARCHAR(255)" + " )");
        statement.close();
        return connection;
    }
