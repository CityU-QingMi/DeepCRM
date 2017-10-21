    public synchronized void setMaxRows(int max) throws SQLException {

        if (isClosed || connection.isClosed) {
            checkClosed();
        }

        if (max < 0) {
            throw JDBCUtil.outOfRangeArgument();
        }
        maxRows = max;
    }
