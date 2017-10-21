    public static FunctionSQL newSQLFunction(String token,
            CompileContext context) {

        int     id              = regularFuncMap.get(token, -1);
        boolean isValueFunction = false;

        if (id == -1) {
            id              = valueFuncMap.get(token, -1);
            isValueFunction = true;
        }

        if (id == -1) {
            return null;
        }

        FunctionSQL function = new FunctionSQL(id);

        if (id == FUNC_VALUE) {
            if (context.currentDomain == null) {
                return null;
            }

            function.dataType = context.currentDomain;
        } else {
            function.isSQLValueFunction = isValueFunction;
        }

        return function;
    }
