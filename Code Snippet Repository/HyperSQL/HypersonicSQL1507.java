    public synchronized int setString(long pos, String str, int offset,
                                      int len) throws SQLException {

        checkClosed();

        if (!isInLimits(str.length(), offset, len)) {
            throw JDBCUtil.outOfRangeArgument();
        }

        if (pos < 1) {
            throw JDBCUtil.outOfRangeArgument("pos: " + pos);
        }

        if (!isWritable) {
            throw JDBCUtil.notUpdatableColumn();
        }

        startUpdate();

        str = str.substring(offset, offset + len);

        try {
            clob.setString(session, pos - 1, str);

            return len;
        } catch (HsqlException e) {
            throw JDBCUtil.sqlException(e);
        }
    }
