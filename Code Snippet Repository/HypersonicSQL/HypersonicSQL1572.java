    public synchronized boolean getAutoCommit() throws SQLException {

        checkClosed();

        try {
            return sessionProxy.isAutoCommit();
        } catch (HsqlException e) {
            throw JDBCUtil.sqlException(e);
        }
    }
