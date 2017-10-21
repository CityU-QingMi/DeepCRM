    public synchronized void registerOutParameter(int parameterIndex,
            int sqlType) throws SQLException {

        checkGetParameterIndex(parameterIndex);

        if (parameterModes[--parameterIndex]
                == SchemaObject.ParameterModes.PARAM_IN) {
            throw JDBCUtil.invalidArgument();
        }
    }
