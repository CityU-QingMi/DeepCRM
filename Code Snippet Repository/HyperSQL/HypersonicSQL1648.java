    protected void checkGetParameterIndex(int i) throws SQLException {

        String msg;

        if (isClosed || connection.isClosed) {
            checkClosed();
        }

        if (i < 1 || i > parameterValues.length) {
            msg = "parameter index out of range: " + i;

            throw JDBCUtil.outOfRangeArgument(msg);
        }

        int mode = parameterModes[i - 1];

        switch (mode) {

            case SchemaObject.ParameterModes.PARAM_UNKNOWN :
            case SchemaObject.ParameterModes.PARAM_OUT :
            case SchemaObject.ParameterModes.PARAM_INOUT :
                break;
            case SchemaObject.ParameterModes.PARAM_IN :
            default :
                msg = "Not OUT or INOUT mode: " + mode + " for parameter: "
                      + i;

                throw JDBCUtil.invalidArgument(msg);
        }
    }
