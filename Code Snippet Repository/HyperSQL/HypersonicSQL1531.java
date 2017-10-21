    public long position(final char[] pattern,
                         final long start) throws SQLException {

        if (start < 1) {
            throw JDBCUtil.outOfRangeArgument("start: " + start);
        } else if (pattern == null || pattern.length == 0) {
            return -1L;
        }

        long length = this.length();

        if (start > length || pattern.length > length || start > length
                - pattern.length) {
            return -1;
        }

        return position0(new String(pattern), start);
    }
