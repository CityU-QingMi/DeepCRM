    Statement compileLoop(Routine routine, StatementCompound context,
                          HsqlName label) {

        readThis(Tokens.LOOP);

        Statement[] statements = compileSQLProcedureStatementList(routine,
            context);

        readThis(Tokens.END);
        readThis(Tokens.LOOP);

        if (isSimpleName() && !isReservedKey()) {
            if (label == null) {
                throw unexpectedToken();
            }

            if (!label.name.equals(token.tokenString)) {
                throw Error.error(ErrorCode.X_42508, token.tokenString);
            }

            read();
        }

        StatementCompound result = new StatementCompound(StatementTypes.LOOP,
            label, context);

        result.setStatements(statements);

        return result;
    }
