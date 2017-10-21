    public boolean absolute(int row) throws SQLException {

        checkClosed();
        checkNotForwardOnly();

        if (isOnInsertRow || isRowUpdated) {
            throw JDBCUtil.sqlExceptionSQL(ErrorCode.X_24513);
        }

        if (row > 0) {
            row--;
        } else if (row == 0) {
            return navigator.beforeFirst();
        }

        return navigator.absolute(row);
    }
