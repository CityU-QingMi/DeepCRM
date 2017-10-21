    Expression XreadBooleanPredicand() {

        Expression e;

        if (token.tokenType == Tokens.OPENBRACKET) {
            read();

            e = XreadBooleanValueExpression();

            readThis(Tokens.CLOSEBRACKET);

            return e;
        } else {
            e = XreadSimpleValueExpressionPrimary();

            if (e != null) {
                e = XreadArrayElementReference(e);
            }

            return e;
        }
    }
