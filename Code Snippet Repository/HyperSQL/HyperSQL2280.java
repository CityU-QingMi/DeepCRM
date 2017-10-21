    private Statement compileSavepoint() {

        String name;

        read();
        checkIsSimpleName();

        name = token.tokenString;

        read();

        Object[] args = new Object[]{ name };

        return new StatementSession(StatementTypes.SAVEPOINT, args);
    }
