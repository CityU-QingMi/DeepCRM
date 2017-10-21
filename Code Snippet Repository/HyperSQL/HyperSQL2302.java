    private Statement compileSetRole() {

        Expression e;

        if (token.tokenType == Tokens.NONE) {
            read();

            e = new ExpressionValue(null, Type.SQL_VARCHAR);
        } else {
            e = XreadValueSpecificationOrNull();

            if (e == null) {
                throw Error.error(ErrorCode.X_2A000);
            }

            if (!e.getDataType().isCharacterType()) {
                throw Error.error(ErrorCode.X_0P000);
            }

            if (e.getType() != OpTypes.VALUE
                    && (e.getType() != OpTypes.SQL_FUNCTION
                        || !((FunctionSQL) e).isValueFunction())) {
                throw Error.error(ErrorCode.X_0P000);
            }
        }

        return new StatementSession(session, compileContext,
                                    StatementTypes.SET_ROLE,
                                    new Expression[]{ e });
    }
