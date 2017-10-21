    public synchronized InputStream getBinaryStream(long pos,
            long length) throws SQLException {

        checkClosed();

        if (!isInLimits(this.length(), pos - 1, length)) {
            throw JDBCUtil.outOfRangeArgument();
        }

        return new BlobInputStream(session, blob, pos - 1, length);
    }
