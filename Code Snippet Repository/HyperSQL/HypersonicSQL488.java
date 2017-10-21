    HsqlArrayList readCaseWhen(Routine routine, StatementCompound context) {

        HsqlArrayList list      = new HsqlArrayList();
        Expression    condition = null;
        Statement     statement;
        Statement[]   statements;

        do {
            readThis(Tokens.WHEN);

            condition = XreadBooleanValueExpression();

            resolveOuterReferencesAndTypes(routine, context, condition);

            statement = new StatementExpression(session, compileContext,
                                                StatementTypes.CONDITION,
                                                condition);

            list.add(statement);
            readThis(Tokens.THEN);

            statements = compileSQLProcedureStatementList(routine, context);

            for (int i = 0; i < statements.length; i++) {
                list.add(statements[i]);
            }

            if (token.tokenType != Tokens.WHEN) {
                break;
            }
        } while (true);

        return list;
    }
