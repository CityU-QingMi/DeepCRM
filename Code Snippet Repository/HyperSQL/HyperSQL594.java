    public long position(String searchstr, long start) throws SQLException {
        if (start < 1) {
            throw JDBCUtil.outOfRangeArgument("start: " + start);
        } else if (searchstr == null || searchstr.length() == 0) {
            return -1L;
        }

        final long length = this.length();
        final int searchstrLength = searchstr.length();

        if (start > length || searchstrLength > length || start > length
                - searchstrLength) {
            return -1;
        }
        return position0(searchstr, start);
    }
