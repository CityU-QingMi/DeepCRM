    Expression readNextvalFunction() {

        read();
        readThis(Tokens.OPENBRACKET);

        String  spec    = readQuotedString();
        Scanner scanner = session.getScanner();

        scanner.reset(session, spec);
        scanner.scanNext();

        String schemaName = session.getSchemaName(scanner.token.namePrefix);
        NumberSequence sequence =
            database.schemaManager.getSequence(scanner.token.tokenString,
                                               schemaName, true);
        Expression e = new ExpressionColumn(sequence, OpTypes.SEQUENCE);

        readThis(Tokens.CLOSEBRACKET);

        return e;
    }
