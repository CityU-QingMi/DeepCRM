    public long position(final Blob pattern,
                         final long start) throws SQLException {

        if (start < 1) {
            throw JDBCUtil.outOfRangeArgument("start: " + start);
        }

        if (pattern == null) {
            return -1;
        }

        final long patternLength = pattern.length();

        if (patternLength == 0) {
            return -1;
        } else if (patternLength > Integer.MAX_VALUE) {
            throw JDBCUtil.outOfRangeArgument("pattern.length(): "
                    + patternLength);
        }

        final long length = this.length();

        if (start > length || patternLength > length || start > length
                - patternLength) {
            return -1;
        }

        final byte[] bytePattern = (pattern instanceof JDBCBlob)
                ? ((JDBCBlob) pattern).data()
                : pattern.getBytes(1L, (int) patternLength);

        return position0(bytePattern, start);
    }
