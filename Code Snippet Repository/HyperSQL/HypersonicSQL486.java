    Statement compileFor(Routine routine, StatementCompound context,
                         HsqlName label) {

        RangeGroup[] rangeGroups = new RangeGroup[1];

        rangeGroups[0] = context == null ? routine
                                         : context;

        compileContext.setOuterRanges(rangeGroups);
        readThis(Tokens.FOR);

        StatementQuery cursorStatement =
            compileCursorSpecification(rangeGroups,
                                       ResultProperties.defaultPropsValue,
                                       false);

        readThis(Tokens.DO);

        StatementCompound forStatement =
            new StatementCompound(StatementTypes.FOR, label, context);

        forStatement.setAtomic(true);
        forStatement.setRoot(routine);
        forStatement.setLoopStatement(null, cursorStatement);

        Statement[] statements = compileSQLProcedureStatementList(routine,
            forStatement);

        readThis(Tokens.END);
        readThis(Tokens.FOR);

        if (isSimpleName() && !isReservedKey()) {
            if (label == null) {
                throw unexpectedToken();
            }

            if (!label.name.equals(token.tokenString)) {
                throw Error.error(ErrorCode.X_42508, token.tokenString);
            }

            read();
        }

        forStatement.setStatements(statements);

        return forStatement;
    }
