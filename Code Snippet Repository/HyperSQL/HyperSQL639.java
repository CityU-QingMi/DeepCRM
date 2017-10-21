    public synchronized void commit() throws SQLException {

        checkClosed();

        try {
            sessionProxy.commit(false);
        } catch (HsqlException e) {
            throw JDBCUtil.sqlException(e);
        }
    }
