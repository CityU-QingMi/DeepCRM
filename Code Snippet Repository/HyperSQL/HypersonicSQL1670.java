    public void beforeFirst() throws SQLException {

        checkClosed();
        checkNotForwardOnly();

        if (isOnInsertRow || isRowUpdated) {
            throw JDBCUtil.sqlExceptionSQL(ErrorCode.X_24513);
        }
        navigator.beforeFirst();
    }
