    public long position(final byte[] pattern,
                         final long start) throws SQLException {

        final byte[] data = getData();
        final int    dlen = data.length;

        if (start < MIN_POS) {
            throw JDBCUtil.outOfRangeArgument("start: " + start);
        } else if (start > dlen || pattern == null) {
            return -1L;
        }

        // by now, we know start <= Integer.MAX_VALUE;
        final int startIndex = (int) start - 1;
        final int plen       = pattern.length;

        if (plen == 0 || startIndex > dlen - plen) {
            return -1L;
        }

        final int result = KMPSearchAlgorithm.search(data, pattern,
            KMPSearchAlgorithm.computeTable(pattern), startIndex);

        return (result == -1) ? -1
                              : result + 1;
    }
