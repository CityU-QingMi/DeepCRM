    private Statement compileReleaseSavepoint() {

        read();
        readThis(Tokens.SAVEPOINT);

        String name = token.tokenString;

        read();

        Object[] args = new Object[]{ name };

        return new StatementSession(StatementTypes.RELEASE_SAVEPOINT, args);
    }
