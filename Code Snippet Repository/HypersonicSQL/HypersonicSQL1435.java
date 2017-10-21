    public byte[] getBytes(long pos, final int length) throws SQLException {

        final byte[] data = getData();
        final int    dlen = data.length;

        if (pos < MIN_POS || pos - MIN_POS > dlen) {
            throw JDBCUtil.outOfRangeArgument("pos: " + pos);
        }

        final int index = (int) pos - 1;

        if (length < 0 || length > dlen - index) {
            throw JDBCUtil.outOfRangeArgument("length: " + length);
        }

        final byte[] result = new byte[length];

        System.arraycopy(data, index, result, 0, length);

        return result;
    }
