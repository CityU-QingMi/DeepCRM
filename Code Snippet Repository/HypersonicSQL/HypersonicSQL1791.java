    synchronized public Connection getConnection() throws SQLException {

        if (isInUse) {
            throw new SQLException("Connection in use");
        }

        isInUse = true;

        if (connection == null) {
            throw new SQLException("Connection in closed");
        }

        return new JDBCXAConnectionWrapper(xaResource, this, connection);
    }
