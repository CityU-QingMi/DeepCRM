    Result executeJavaProcedure(Session session, Connection connection) {

        Result   result        = Result.updateZeroResult;
        Object[] callArguments = session.sessionContext.routineArguments;
        Object[] data = procedure.convertArgsToJava(session, callArguments);

        if (procedure.javaMethodWithConnection) {
            data[0] = connection;
        }

        result = procedure.invokeJavaMethod(session, data);

        procedure.convertArgsToSQL(session, callArguments, data);

        return result;
    }
