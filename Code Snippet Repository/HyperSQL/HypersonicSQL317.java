    private Statement compileConnect() {

        String userName;
        String password = null;

        read();
        readThis(Tokens.USER);
        checkIsSimpleName();

        userName = token.tokenString;

        read();

        if (!session.isProcessingLog()) {
            readThis(Tokens.PASSWORD);

            password = readPassword();
        }

        Expression[] args = new Expression[] {
            new ExpressionValue(userName, Type.SQL_VARCHAR),
            new ExpressionValue(password, Type.SQL_VARCHAR)
        };
        Statement cs =
            new StatementSession(session, compileContext,
                                 StatementTypes.SET_SESSION_AUTHORIZATION,
                                 args);

        return cs;
    }
