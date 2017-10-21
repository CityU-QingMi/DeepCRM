    public synchronized int getResultSetType() throws SQLException {

        // fredt - omit checkClosed() in order to be able to handle the result of a
        // SHUTDOWN query
        if (isClosed || connection.isClosed) {
            checkClosed();
        }

        return ResultProperties.getJDBCScrollability(rsProperties);
    }
