    public synchronized long length() throws SQLException {

        checkClosed();

        try {
            return blob.length(session);
        } catch (HsqlException e) {
            throw JDBCUtil.sqlException(e);
        }
    }
