    public synchronized int setBytes(long pos, byte[] bytes, int offset,
                                     int len) throws SQLException {

        checkClosed();

        if (!isInLimits(bytes.length, offset, len)) {
            throw JDBCUtil.outOfRangeArgument();
        }

        if (!isInLimits(Long.MAX_VALUE, pos - 1, len)) {
            throw JDBCUtil.outOfRangeArgument();
        }

        if (!isWritable) {
            throw JDBCUtil.notUpdatableColumn();
        }

        startUpdate();

        try {
            blob.setBytes(session, pos - 1, bytes, offset, len);

            return len;
        } catch (HsqlException e) {
            throw JDBCUtil.sqlException(e);
        }
    }
