    public synchronized int getTransactionIsolation() throws SQLException {

        checkClosed();

        try {
            return sessionProxy.getIsolation();
        } catch (HsqlException e) {
            throw JDBCUtil.sqlException(e);
        }
    }
