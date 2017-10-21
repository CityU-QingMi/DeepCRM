    Expression XreadLateralDerivedTable() {

        readThis(Tokens.LATERAL);
        readThis(Tokens.OPENBRACKET);

        TableDerived td = XreadSubqueryTableBody(OpTypes.TABLE_SUBQUERY);

        readThis(Tokens.CLOSEBRACKET);

        return new Expression(OpTypes.TABLE_SUBQUERY, td);
    }
