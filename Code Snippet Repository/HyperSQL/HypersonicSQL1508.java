    public synchronized void truncate(long len) throws SQLException {

        checkClosed();

        if (len < 0) {
            throw JDBCUtil.outOfRangeArgument("len: " + len);
        }

        try {
            clob.truncate(session, len);
        } catch (HsqlException e) {
            throw JDBCUtil.sqlException(e);
        }
    }
