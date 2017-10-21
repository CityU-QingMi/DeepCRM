    Expression XreadCollectionDerivedTable(int type) {

        boolean ordinality = false;
        int     position   = getPosition();

        readThis(Tokens.UNNEST);
        readThis(Tokens.OPENBRACKET);
        compileContext.incrementDepth();

        HsqlArrayList list = new HsqlArrayList();

        while (true) {
            Expression e = XreadValueExpression();

            list.add(e);

            if (token.tokenType == Tokens.COMMA) {
                read();
            } else {
                break;
            }
        }

        Expression[] array = new Expression[list.size()];

        list.toArray(array);
        readThis(Tokens.CLOSEBRACKET);

        if (token.tokenType == Tokens.WITH) {
            read();
            readThis(Tokens.ORDINALITY);

            ordinality = true;
        }

        Expression   e  = new ExpressionTable(array, ordinality);
        TableDerived td = newSubQueryTable(e, type);

        td.setSQL(getLastPart(position));
        compileContext.decrementDepth();

        return e;
    }
