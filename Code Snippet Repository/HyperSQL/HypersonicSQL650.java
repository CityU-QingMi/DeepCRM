    public Result invokeJavaMethodDirect(Object[] data) {

        Result result;

        try {
            Object returnValue = javaMethod.invoke(null, data);

            returnValue = returnType.convertJavaToSQL(null, returnValue);
            result      = Result.newPSMResult(returnValue);
        } catch (Throwable t) {
            result = Result.newErrorResult(Error.error(t, ErrorCode.X_46000,
                    getName().name));
        }

        return result;
    }
