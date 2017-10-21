    private Statement compileStartTransaction() {

        read();
        readThis(Tokens.TRANSACTION);

        Object[] args = processTransactionCharacteristics();
        Statement cs = new StatementSession(StatementTypes.START_TRANSACTION,
                                            args);

        return cs;
    }
