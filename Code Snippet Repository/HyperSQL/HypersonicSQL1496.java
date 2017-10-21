    public long position(final Clob searchstr,
                         final long start) throws SQLException {

        final String data = getData();

        if (start < MIN_POS) {
            throw JDBCUtil.outOfRangeArgument("start: " + start);
        }

        if (searchstr == null) {
            return -1;
        }

        final long dlen  = data.length();
        final long sslen = searchstr.length();
        final long startIndex = start-1;

        // This is potentially much less expensive than materializing a large
        // substring from some other vendor's CLOB.  Indeed, we should probably
        // do the comparison piecewise, using an in-memory buffer (or temp-files
        // when available), if it is detected that the input CLOB is very long.
        if (startIndex > dlen - sslen) {
            return -1;
        }

        // by now, we know sslen and startIndex are both < Integer.MAX_VALUE
        String pattern;

        if (searchstr instanceof JDBCClob) {
            pattern = ((JDBCClob) searchstr).getData();
        } else {
            pattern = searchstr.getSubString(1L, (int) sslen);
        }

        final int index = data.indexOf(pattern, (int) startIndex);

        return (index == -1) ? -1
                                : index + 1;
    }
