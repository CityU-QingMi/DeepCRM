    public synchronized void setAutoCommit(
            boolean autoCommit) throws SQLException {

        checkClosed();

        try {
            sessionProxy.setAutoCommit(autoCommit);
        } catch (HsqlException e) {
            throw JDBCUtil.sqlException(e);
        }
    }
