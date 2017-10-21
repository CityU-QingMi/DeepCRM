    Object[] convertArgsToJava(Session session, Object[] callArguments) {

        int      extraArg = javaMethodWithConnection ? 1
                                                     : 0;
        Object[] data     = new Object[javaMethod.getParameterTypes().length];
        Type[]   types    = getParameterTypes();
        int      i        = 0;

        for (; i < types.length; i++) {
            Object       value = callArguments[i];
            ColumnSchema param = getParameter(i);

            if (param.parameterMode == SchemaObject.ParameterModes.PARAM_IN) {
                data[i + extraArg] = types[i].convertSQLToJava(session, value);
            } else {
                Object jdbcValue = types[i].convertSQLToJava(session, value);
                Class  cl        = types[i].getJDBCClass();
                Object array     = java.lang.reflect.Array.newInstance(cl, 1);

                java.lang.reflect.Array.set(array, 0, jdbcValue);

                data[i + extraArg] = array;
            }
        }

        for (; i + extraArg < data.length; i++) {
            data[i + extraArg] = new java.sql.ResultSet[1];
        }

        return data;
    }
