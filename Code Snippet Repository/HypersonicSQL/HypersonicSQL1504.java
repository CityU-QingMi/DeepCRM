    public synchronized long position(String searchstr,
                                      long start) throws SQLException {

        checkClosed();

        if (!isInLimits(Long.MAX_VALUE, start - 1, 0)) {
            throw JDBCUtil.outOfRangeArgument();
        }

        try {
            return clob.position(session, searchstr, start - 1);
        } catch (HsqlException e) {
            throw JDBCUtil.sqlException(e);
        }
    }
