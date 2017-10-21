    private long position0(final byte[] pattern, final long start) throws
            SQLException {
        InputStream is = null;

        try {
            is = getBinaryStream(start, Long.MAX_VALUE);

            //@todo maybe single-byte encoding reader
            //      and java.util.Scanner.findWithinHorizon.
            //      Need to do comparative benchmark and unit
            //      tests first.
            final long matchOffset = KMPSearchAlgorithm.search(is, pattern,
                    KMPSearchAlgorithm.computeTable(pattern));

            return (matchOffset == -1) ? -1
                    : start + matchOffset;
        } catch (IOException ex) {
            throw JDBCUtil.sqlException(ex);
        } finally {
            closeSafely(is);
        }
    }
