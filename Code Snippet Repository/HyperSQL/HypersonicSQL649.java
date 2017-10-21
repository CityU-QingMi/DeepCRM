    void convertArgsToSQL(Session session, Object[] callArguments,
                          Object[] data) {

        int    extraArg = javaMethodWithConnection ? 1
                                                   : 0;
        Type[] types    = getParameterTypes();
        int    i        = 0;

        for (; i < types.length; i++) {
            Object       value = data[i + extraArg];
            ColumnSchema param = getParameter(i);

            if (param.parameterMode != SchemaObject.ParameterModes.PARAM_IN) {
                value = java.lang.reflect.Array.get(value, 0);
            }

            callArguments[i] = types[i].convertJavaToSQL(session, value);
        }

        Result head = null;

        for (; i + extraArg < data.length; i++) {
            ResultSet rs = ((ResultSet[]) data[i + extraArg])[0];

            if (rs != null) {
                if (rs instanceof JDBCResultSet) {
                    Result r = ((JDBCResultSet) rs).result;

                    if (head == null) {
                        callArguments[i] = r;
                        head             = r;
                    } else {
                        head.addChainedResult(r);
                    }
                } else {
                    throw Error.error(ErrorCode.X_46000,
                                      "ResultSet not native");
                }
            }
        }
    }
