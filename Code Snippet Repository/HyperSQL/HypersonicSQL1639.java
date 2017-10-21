    public synchronized void setQueryTimeout(int seconds) throws SQLException {

        if (isClosed || connection.isClosed) {
            checkClosed();
        }

        if (seconds < 0) {
            throw JDBCUtil.outOfRangeArgument();
        }

        if (seconds > Short.MAX_VALUE) {
            seconds = Short.MAX_VALUE;
        }
        queryTimeout = seconds;
    }
