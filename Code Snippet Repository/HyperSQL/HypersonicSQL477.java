    Statement compileCompoundStatement(Routine routine,
                                       StatementCompound context,
                                       HsqlName label) {

        final boolean atomic = true;

        readThis(Tokens.BEGIN);
        readThis(Tokens.ATOMIC);

        label = createLabelIfNull(context, label);

        StatementCompound statement =
            new StatementCompound(StatementTypes.BEGIN_END, label, context);

        statement.setAtomic(atomic);
        statement.setRoot(routine);

        Object[] declarations = readLocalDeclarationList(routine, context);

        statement.setLocalDeclarations(declarations);

        Statement[] statements = compileSQLProcedureStatementList(routine,
            statement);

        statement.setStatements(statements);
        readThis(Tokens.END);

        if (isSimpleName() && !isReservedKey()) {
            if (label == null) {
                throw unexpectedToken();
            }

            if (!label.name.equals(token.tokenString)) {
                throw Error.error(ErrorCode.X_42508, token.tokenString);
            }

            read();
        }

        return statement;
    }
