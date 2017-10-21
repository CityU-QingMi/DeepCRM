    public synchronized long length() throws SQLException {

        checkClosed();

        try {
            return clob.length(session);
        } catch (HsqlException e) {
            throw JDBCUtil.sqlException(e);
        }
    }
