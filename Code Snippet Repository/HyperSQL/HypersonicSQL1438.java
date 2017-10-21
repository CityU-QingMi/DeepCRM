    public int setBytes(long pos, byte[] bytes, int offset,
                        int len) throws SQLException {

        checkReadonly();

        if (bytes == null) {
            throw JDBCUtil.nullArgument("bytes");
        }

        if (offset < 0 || offset > bytes.length) {
            throw JDBCUtil.outOfRangeArgument("offset: " + offset);
        }

        if (len > bytes.length - offset) {
            throw JDBCUtil.outOfRangeArgument("len: " + len);
        }

        if (pos < MIN_POS || (pos - MIN_POS) > (Integer.MAX_VALUE - len)) {
            throw JDBCUtil.outOfRangeArgument("pos: " + pos);
        }

        final int index = (int) (pos - MIN_POS);
        byte[]    data = getData();
        final int dlen = data.length;

        if (index > dlen - len) {
            byte[] temp = new byte[index + len];

            System.arraycopy(data, 0, temp, 0, dlen);

            data = temp;
            temp = null;
        }

        System.arraycopy(bytes, offset, data, index, len);

        setData(data);

        return len;
    }
