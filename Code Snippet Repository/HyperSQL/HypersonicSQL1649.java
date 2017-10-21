    private void checkParametersSet() throws SQLException {

        if (isResult) {
            return;
        }

        for (int i = 0; i < parameterSet.length; i++) {
            if (parameterModes[i] != SchemaObject.ParameterModes.PARAM_OUT) {
                if (parameterSet[i] == null) {
                    throw JDBCUtil.sqlException(ErrorCode.JDBC_PARAMETER_NOT_SET);
                }
            }
        }
    }
