    public InputStream getBinaryStream(long pos,
                                       long length) throws SQLException {

        final byte[] data = getData();
        final int    dlen = data.length;

        if (pos < MIN_POS || pos > dlen) {
            throw JDBCUtil.outOfRangeArgument("pos: " + pos);
        }

        int index = (int) (pos - MIN_POS);

        if (length < 0 || length > dlen - index) {
            throw JDBCUtil.outOfRangeArgument("length: " + length);
        }

        if (index == 0 && length == dlen) {
            return new ByteArrayInputStream(data);
        }

        final int ilength = (int) length;
        final byte[] result = new byte[ilength];

        System.arraycopy(data, index, result, 0, ilength);

        return new ByteArrayInputStream(result);
    }
