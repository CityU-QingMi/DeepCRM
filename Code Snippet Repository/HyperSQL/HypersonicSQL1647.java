    protected void checkSetParameterIndex(int i) throws SQLException {

        if (isClosed || connection.isClosed) {
            checkClosed();
        }

        if (i < 1 || i > parameterValues.length) {
            String msg = "parameter index out of range: " + i;

            throw JDBCUtil.outOfRangeArgument(msg);
        }

        if (parameterModes[i - 1] == SchemaObject.ParameterModes.PARAM_OUT) {
            String msg = "Not IN or INOUT mode for parameter: " + i;

            throw JDBCUtil.invalidArgument(msg);
        }
    }
