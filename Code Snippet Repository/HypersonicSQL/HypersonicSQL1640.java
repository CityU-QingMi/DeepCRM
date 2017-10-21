    public synchronized void setFetchDirection(
            int direction) throws SQLException {

        if (isClosed || connection.isClosed) {
            checkClosed();
        }

        if (direction != ResultSet.FETCH_FORWARD
                && direction != ResultSet.FETCH_REVERSE
                && direction != ResultSet.FETCH_UNKNOWN) {
            throw JDBCUtil.notSupported();
        }
        fetchDirection = direction;
    }
