    public long position(final String searchstr,
                         long start) throws SQLException {

        final String data = getData();

        if (start < MIN_POS) {
            throw JDBCUtil.outOfRangeArgument("start: " + start);
        }

        if (searchstr == null || start > MAX_POS) {
            return -1;
        }

        final int position = data.indexOf(searchstr, (int)start - 1);

        return (position == -1) ? -1
                                : position + 1;
    }
