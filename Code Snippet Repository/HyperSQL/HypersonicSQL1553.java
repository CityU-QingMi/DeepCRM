    public synchronized void releaseSavepoint(
            Savepoint savepoint) throws SQLException {

        JDBCSavepoint sp;
        Result        req;

        checkClosed();

        if (savepoint == null) {
            throw JDBCUtil.nullArgument();
        }

        if (!(savepoint instanceof JDBCSavepoint)) {
            String msg = Error.getMessage(ErrorCode.X_3B001);

            throw JDBCUtil.invalidArgument(msg);
        }
        sp = (JDBCSavepoint) savepoint;

        if (JDBCDatabaseMetaData.JDBC_MAJOR >= 4 && sp.name == null) {
            String msg = Error.getMessage(ErrorCode.X_3B001);

            throw JDBCUtil.invalidArgument(msg);
        }

        if (this != sp.connection) {
            String msg = Error.getMessage(ErrorCode.X_3B001);

            throw JDBCUtil.invalidArgument(msg);
        }

        if (JDBCDatabaseMetaData.JDBC_MAJOR >= 4 && getAutoCommit()) {
            sp.name       = null;
            sp.connection = null;

            throw JDBCUtil.sqlException(ErrorCode.X_3B001);
        }

        try {
            sessionProxy.releaseSavepoint(sp.name);

            if (JDBCDatabaseMetaData.JDBC_MAJOR >= 4) {
                sp.connection = null;
                sp.name       = null;
            }
        } catch (HsqlException e) {
            throw JDBCUtil.sqlException(e);
        }
    }
