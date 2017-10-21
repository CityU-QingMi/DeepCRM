    public String getSubString(final long pos,
                               final int length) throws SQLException {

        final String data = getData();
        final int    dlen = data.length();

        if (pos == MIN_POS && length == dlen) {
            return data;
        }

        if (pos < MIN_POS || pos > dlen) {
            throw JDBCUtil.outOfRangeArgument("pos: " + pos);
        }

        final long index = pos - 1;

        if (length < 0 || length > dlen - index) {
            throw JDBCUtil.outOfRangeArgument("length: " + length);
        }

        return data.substring((int) index, (int) index + length);
    }
