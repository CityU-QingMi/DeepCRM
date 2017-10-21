    Expression XreadValueExpression() {

        Expression e = XreadAllTypesCommonValueExpression(true);

        if (token.tokenType == Tokens.LEFTBRACKET) {
            read();

            Expression e1 = XreadNumericValueExpression();

            readThis(Tokens.RIGHTBRACKET);

            e = new ExpressionAccessor(e, e1);
        }

        return e;
    }
