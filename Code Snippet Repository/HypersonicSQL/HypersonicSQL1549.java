    public synchronized Savepoint setSavepoint() throws SQLException {

        checkClosed();

        if (JDBCDatabaseMetaData.JDBC_MAJOR >= 4 && getAutoCommit()) {
            throw JDBCUtil.sqlException(ErrorCode.X_3B001);
        }

        JDBCSavepoint savepoint = new JDBCSavepoint(this);

        try {
            sessionProxy.savepoint(savepoint.name);
        } catch (HsqlException e) {
            JDBCUtil.throwError(e);
        }

        return savepoint;
    }
