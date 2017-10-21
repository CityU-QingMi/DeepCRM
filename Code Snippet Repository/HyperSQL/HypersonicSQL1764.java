    public void setQueryTimeout(int seconds) throws SQLException {

        checkClosed();

        if (seconds < 0) {
            throw JDBCUtil.outOfRangeArgument();
        }

        if (seconds > Short.MAX_VALUE) {
            seconds = Short.MAX_VALUE;
        }
        queryTimeout = seconds;
    }
