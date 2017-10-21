    synchronized public Connection getConnection() throws SQLException {

        if (isInUse) {
            throw new SQLException("Connection in use");
        }

        isInUse = true;

        userConnection = new JDBCConnection(connection, this);

        return userConnection;
    }
