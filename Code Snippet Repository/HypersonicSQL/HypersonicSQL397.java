    Expression XreadCharacterValueExpression() {

        Expression e         = XreadCharacterPrimary();
        Collation  collation = readCollateClauseOrNull();

        while (token.tokenType == Tokens.CONCAT_OP) {
            read();

            Expression a = e;

            e         = XreadCharacterPrimary();
            collation = readCollateClauseOrNull();
            e         = new ExpressionArithmetic(OpTypes.CONCAT, a, e);
        }

        return e;
    }
