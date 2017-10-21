    public synchronized void truncate(long len) throws SQLException {

        checkClosed();

        if (!isInLimits(Long.MAX_VALUE, 0, len)) {
            throw JDBCUtil.outOfRangeArgument();
        }

        try {
            blob.truncate(session, len);
        } catch (HsqlException e) {
            throw JDBCUtil.sqlException(e);
        }
    }
