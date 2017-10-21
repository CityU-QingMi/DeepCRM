    private Statement compileResignal(Routine routine,
                                      StatementCompound context,
                                      HsqlName label) {

        String     sqlState = null;
        Expression message  = null;

        readThis(Tokens.RESIGNAL);

        if (readIfThis(Tokens.SQLSTATE)) {
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
        }

        StatementSignal cs = new StatementSignal(StatementTypes.RESIGNAL,
            sqlState, message);

        return cs;
    }
