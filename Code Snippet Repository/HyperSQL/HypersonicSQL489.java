    Statement compileSignal(Routine routine, StatementCompound context,
                            HsqlName label) {

        String     sqlState;
        Expression message = null;

        readThis(Tokens.SIGNAL);
        readThis(Tokens.SQLSTATE);

        sqlState = parseSQLStateValue();

        if (readIfThis(Tokens.SET)) {
            readThis(Tokens.MESSAGE_TEXT);
            readThis(Tokens.EQUALS_OP);

            message = XreadSimpleValueSpecificationOrNull();

            if (message == null) {
                throw unexpectedToken();
            }

            resolveOuterReferencesAndTypes(routine, context, message);
        }

        StatementSignal cs = new StatementSignal(StatementTypes.SIGNAL,
            sqlState, message);

        return cs;
    }
