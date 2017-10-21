    Expression XreadTableFunctionDerivedTable() {

        int position = getPosition();

        readThis(Tokens.TABLE);
        readThis(Tokens.OPENBRACKET);
        compileContext.incrementDepth();

        Expression e = XreadValueExpression();

        if (e.getType() != OpTypes.FUNCTION
                && e.getType() != OpTypes.SQL_FUNCTION) {
            compileContext.decrementDepth();

            throw unexpectedToken(Tokens.T_TABLE);
        }

        readThis(Tokens.CLOSEBRACKET);

        e = new ExpressionTable(new Expression[]{ e }, false);

        TableDerived td = newSubQueryTable(e, OpTypes.TABLE_SUBQUERY);

        td.setSQL(getLastPart(position));
        compileContext.decrementDepth();

        return e;
    }
