    void checkClosed() throws SQLException {

        if (isClosed) {
            throw JDBCUtil.sqlException(ErrorCode.X_07501);
        }

        if (connection.isClosed) {
            close();

            throw JDBCUtil.sqlException(ErrorCode.X_08503);
        }

        if (connectionIncarnation != connection.incarnation) {
            throw JDBCUtil.sqlException(ErrorCode.X_08503);
        }
    }
