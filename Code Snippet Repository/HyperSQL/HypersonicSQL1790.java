    public PooledConnection getPooledConnection(String user,
            String password) throws SQLException {

        Properties props = new Properties();

        props.setProperty("user", user);
        props.setProperty("password", password);

        JDBCConnection connection =
            (JDBCConnection) JDBCDriver.getConnection(url, props);

        return new JDBCPooledConnection(connection);
    }
