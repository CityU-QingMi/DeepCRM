    Expression XreadDateTimeValueFunctionOrNull() {

        FunctionSQL function = null;

        switch (token.tokenType) {

            case Tokens.CURRENT_DATE :
            case Tokens.CURRENT_TIME :
            case Tokens.CURRENT_TIMESTAMP :
            case Tokens.LOCALTIME :
            case Tokens.LOCALTIMESTAMP :
                function = FunctionSQL.newSQLFunction(token.tokenString,
                                                      compileContext);
                break;

            case Tokens.SYSTIMESTAMP :
            case Tokens.NOW :
            case Tokens.TODAY :
            case Tokens.SYSDATE :
                function = FunctionCustom.newCustomFunction(session,
                        token.tokenString, token.tokenType);

                if (function == null) {
                    return null;
                }
                break;

            default :
                return null;
        }

        if (function == null) {
            throw unexpectedToken();
        }

        return readSQLFunction(function);
    }
