    public synchronized String getSubString(long pos,
            int length) throws SQLException {

        checkClosed();

        if (!isInLimits(Long.MAX_VALUE, pos - 1, length)) {
            throw JDBCUtil.outOfRangeArgument();
        }

        try {
            return clob.getSubString(session, pos - 1, length);
        } catch (HsqlException e) {
            throw JDBCUtil.sqlException(e);
        }
    }
