    public synchronized void setMaxFieldSize(int max) throws SQLException {

        if (isClosed || connection.isClosed) {
            checkClosed();
        }

        if (max < 0) {
            throw JDBCUtil.outOfRangeArgument();
        }
    }
