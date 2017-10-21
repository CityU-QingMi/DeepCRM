    Expression XreadArrayConstructor() {

        readThis(Tokens.OPENBRACKET);

        TableDerived td = XreadSubqueryTableBody(OpTypes.TABLE_SUBQUERY);

        readThis(Tokens.CLOSEBRACKET);

        return new Expression(OpTypes.ARRAY_SUBQUERY, td);
    }
