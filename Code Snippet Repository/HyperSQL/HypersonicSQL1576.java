    public synchronized void setReadOnly(
            boolean readOnly) throws SQLException {

        checkClosed();

        try {
            sessionProxy.setReadOnlyDefault(readOnly);
        } catch (HsqlException e) {
            throw JDBCUtil.sqlException(e);
        }
    }
