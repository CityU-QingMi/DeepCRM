    public synchronized void setFetchSize(int rows) throws SQLException {

        if (isClosed || connection.isClosed) {
            checkClosed();
        }

        if (rows < 0) {
            throw JDBCUtil.outOfRangeArgument();
        }
        fetchSize = rows;
    }
