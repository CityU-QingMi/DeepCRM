    public long position(final Blob pattern, long start) throws SQLException {

        final byte[] data = getData();
        final int    dlen = data.length;

        if (start < MIN_POS) {
            throw JDBCUtil.outOfRangeArgument("start: " + start);
        } else if (start > dlen || pattern == null) {
            return -1L;
        }

        // by now, we know start <= Integer.MAX_VALUE;
        final int  startIndex = (int) (start - MIN_POS);
        final long plen       = pattern.length();

        if (plen == 0 || startIndex > ((long) dlen) - plen) {
            return -1L;
        }

        // by now, we know plen <= Integer.MAX_VALUE
        final int iplen = (int) plen;
        byte[]    bytePattern;

        if (pattern instanceof JDBCBlob) {
            bytePattern = ((JDBCBlob) pattern).data();
        } else {
            bytePattern = pattern.getBytes(1L, iplen);
        }

        final int result = KMPSearchAlgorithm.search(data, bytePattern,
            KMPSearchAlgorithm.computeTable(bytePattern), startIndex);

        return (result == -1) ? -1
                              : result + 1;
    }
