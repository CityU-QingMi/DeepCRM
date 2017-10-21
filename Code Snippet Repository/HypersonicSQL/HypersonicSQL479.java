    Statement[] compileSQLProcedureStatementList(Routine routine,
            StatementCompound context) {

        Statement     e;
        HsqlArrayList list = new HsqlArrayList();

        while (true) {
            e = compileSQLProcedureStatementOrNull(routine, context);

            if (e == null) {
                break;
            }

            readThis(Tokens.SEMICOLON);
            list.add(e);
        }

        if (list.size() == 0) {
            throw unexpectedToken();
        }

        Statement[] statements = new Statement[list.size()];

        list.toArray(statements);

        return statements;
    }
