    Expression readFunction() {

        FunctionSQL function = FunctionCustom.newCustomFunction(session,
            token.tokenString, token.tokenType);

        if (function != null) {
            int pos = getPosition();

            try {
                Expression e = readSQLFunction(function);

                if (e != null) {
                    return e;
                }
            } catch (HsqlException ex) {
                ex.setLevel(compileContext.subqueryDepth);

                if (lastError == null
                        || lastError.getLevel() < ex.getLevel()) {
                    lastError = ex;
                }

                rewind(pos);
            }
        } else if (isReservedKey()) {
            function = FunctionSQL.newSQLFunction(token.tokenString,
                                                  compileContext);

            if (function != null) {
                Expression e = readSQLFunction(function);

                if (e != null) {
                    return e;
                }
            }
        }

        return null;
    }
