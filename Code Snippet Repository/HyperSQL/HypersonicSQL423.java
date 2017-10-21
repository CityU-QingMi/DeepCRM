    Expression XreadTableSubquery(int mode) {

        readThis(Tokens.OPENBRACKET);

        TableDerived td = XreadSubqueryTableBody(mode);

        readThis(Tokens.CLOSEBRACKET);

        return new Expression(OpTypes.TABLE_SUBQUERY, td);
    }
