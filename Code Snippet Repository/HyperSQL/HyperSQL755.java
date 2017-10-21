    public boolean previous() throws SQLException {

        checkClosed();
        checkNotForwardOnly();

        if (isOnInsertRow || isRowUpdated) {
            throw JDBCUtil.sqlExceptionSQL(ErrorCode.X_24513);
        }
        rootWarning = null;

        return navigator.previous();
    }
