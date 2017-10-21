    public synchronized long position(byte[] pattern,
                                      long start) throws SQLException {

        checkClosed();

        if (!isInLimits(Long.MAX_VALUE, start - 1, 0)) {
            throw JDBCUtil.outOfRangeArgument();
        }

        try {
            long position = blob.position(session, pattern, start - 1);

            if (position >= 0) {
                position++;
            }

            return position;
        } catch (HsqlException e) {
            throw JDBCUtil.sqlException(e);
        }
    }
