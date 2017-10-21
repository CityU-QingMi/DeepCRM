    public Object getArray(long index, int count) throws SQLException {

        checkClosed();

        if (!JDBCClobClient.isInLimits(data.length, index - 1, count)) {
            throw JDBCUtil.outOfRangeArgument();
        }

        Object[] slice = new Object[count];

        for (int i = 0; i < count; i++) {
            slice[i] = data[(int) index + i - 1];
        }

        return slice;
    }
