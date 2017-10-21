    protected void checkParameterIndex(int i) throws SQLException {

        if (isClosed || connection.isClosed) {
            checkClosed();
        }

        if (i < 1 || i > parameterValues.length) {
            String msg = "parameter index out of range: " + i;

            throw JDBCUtil.outOfRangeArgument(msg);
        }
    }
