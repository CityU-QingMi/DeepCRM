    public boolean relative(int rows) throws SQLException {

        checkClosed();
        checkNotForwardOnly();

        if (isOnInsertRow || isRowUpdated) {
            throw JDBCUtil.sqlExceptionSQL(ErrorCode.X_24513);
        }

        return navigator.relative(rows);
    }
