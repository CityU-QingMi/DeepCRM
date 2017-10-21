    public synchronized void rollback() throws SQLException {

        checkClosed();

        try {
            sessionProxy.rollback(false);
        } catch (HsqlException e) {
            throw JDBCUtil.sqlException(e);
        }
    }
