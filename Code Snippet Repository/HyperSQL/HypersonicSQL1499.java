    public void truncate(final long len) throws SQLException {
        checkReadonly();

        final String data = getData();
        final long dlen = data.length();

        if (len == dlen) {
            return;
        }

        if (len < 0 || len > dlen) {
            throw JDBCUtil.outOfRangeArgument("len: " + len);
        }

        setData(data.substring(0, (int) len));

    }
