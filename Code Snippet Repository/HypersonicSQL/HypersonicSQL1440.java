    public void truncate(final long len) throws SQLException {

        checkReadonly();

        final byte[] data = getData();

        if (len < 0 || len > data.length) {
            throw JDBCUtil.outOfRangeArgument("len: " + len);
        } else if (len == data.length) {
            return;
        }

        byte[] newData = new byte[(int) len];

        System.arraycopy(data, 0, newData, 0, (int) len);

        setData(newData);
    }
