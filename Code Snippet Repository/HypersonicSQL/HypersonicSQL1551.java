    public synchronized Savepoint setSavepoint(
            String name) throws SQLException {

        checkClosed();

        if (JDBCDatabaseMetaData.JDBC_MAJOR >= 4 && getAutoCommit()) {
            throw JDBCUtil.sqlException(ErrorCode.X_3B001);
        }

        if (name == null) {
            throw JDBCUtil.nullArgument();
        }

        if (name.startsWith("SYSTEM_SAVEPOINT_")) {
            throw JDBCUtil.invalidArgument();
        }

        try {
            sessionProxy.savepoint(name);
        } catch (HsqlException e) {
            JDBCUtil.throwError(e);
        }

        return new JDBCSavepoint(name, this);
    }
