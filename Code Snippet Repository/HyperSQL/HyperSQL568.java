    public synchronized Reader getCharacterStream(long pos,
            long length) throws SQLException {

        checkClosed();

        if (!isInLimits(this.length(), pos - 1, length)) {
            throw JDBCUtil.outOfRangeArgument();
        }

        return new ClobInputStream(session, clob, pos - 1, length);
    }
