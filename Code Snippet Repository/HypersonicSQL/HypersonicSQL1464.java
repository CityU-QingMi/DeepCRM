    public long position(final byte[] pattern,
                         final long start) throws SQLException {

        if (start < 1) {
            throw JDBCUtil.outOfRangeArgument("start: " + start);
        } else if (pattern == null || pattern.length == 0) {
            return -1L;
        }

        final long length = this.length();

        if (start > length || pattern.length > length || start > length
                - pattern.length) {
            return -1;
        }

        return position0(pattern, start);
    }
