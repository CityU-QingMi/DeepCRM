    Statement compileRepeat(Routine routine, StatementCompound context,
                            HsqlName label) {

        readThis(Tokens.REPEAT);

        Statement[] statements = compileSQLProcedureStatementList(routine,
            context);

        readThis(Tokens.UNTIL);

        Expression e = XreadBooleanValueExpression();

        resolveOuterReferencesAndTypes(routine, context, e);

        StatementExpression condition = new StatementExpression(session,
            compileContext, StatementTypes.CONDITION, e);

        readThis(Tokens.END);
        readThis(Tokens.REPEAT);

        if (isSimpleName() && !isReservedKey()) {
            if (label == null) {
                throw unexpectedToken();
            }

            if (!label.name.equals(token.tokenString)) {
                throw Error.error(ErrorCode.X_42508, token.tokenString);
            }

            read();
        }

        StatementCompound statement =
            new StatementCompound(StatementTypes.REPEAT, label, context);

        statement.setStatements(statements);
        statement.setCondition(condition);

        return statement;
    }
