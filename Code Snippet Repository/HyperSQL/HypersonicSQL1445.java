    public synchronized long position(Blob pattern,
                                      long start) throws SQLException {

        checkClosed();

        if (!isInLimits(Long.MAX_VALUE, start - 1, 0)) {
            throw JDBCUtil.outOfRangeArgument();
        }

        if (pattern instanceof JDBCBlobClient) {
            BlobDataID searchClob = ((JDBCBlobClient) pattern).blob;

            try {
                long position = blob.position(session, searchClob, start - 1);

                if (position >= 0) {
                    position++;
                }

                return position;
            } catch (HsqlException e) {
                throw JDBCUtil.sqlException(e);
            }
        }

        if (!isInLimits(Integer.MAX_VALUE, 0, pattern.length())) {
            throw JDBCUtil.outOfRangeArgument();
        }

        byte[] bytePattern = pattern.getBytes(1, (int) pattern.length());

        return position(bytePattern, start);
    }
