    public synchronized boolean isReadOnly() throws SQLException {

        checkClosed();

        try {
            return sessionProxy.isReadOnlyDefault();
        } catch (HsqlException e) {
            throw JDBCUtil.sqlException(e);
        }
    }
