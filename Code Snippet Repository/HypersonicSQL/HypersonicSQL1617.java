    public Connection getConnection() throws SQLException {

        int retries = 300;

        if (source.loginTimeout != 0){
            retries = source.loginTimeout * 10;
        }

        if (closed) {
            throw new SQLException("connection pool is closed");
        }

        for (int count = 0; count < retries; count++) {
            for (int i = 0; i < states.length(); i++) {
                if (states.compareAndSet(i, RefState.available,
                                        RefState.allocated)) {
                    return connections[i].getConnection();
                }

                if (states.compareAndSet(i, RefState.empty,
                                        RefState.allocated)) {
                    try {
                        JDBCPooledConnection connection =
                            (JDBCPooledConnection) source.getPooledConnection();

                        connection.addConnectionEventListener(this);
                        connection.addStatementEventListener(this);
                        connections[i] = connection;

                        return connections[i].getConnection();
                    } catch (SQLException e) {
                        states.set(i, RefState.empty);
                    }
                }
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        throw JDBCUtil.invalidArgument();
    }
