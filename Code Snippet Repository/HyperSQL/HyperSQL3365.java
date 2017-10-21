    Expression XreadBooleanFactorOrNull() {

        Expression e;
        boolean    not = false;

        if (token.tokenType == Tokens.NOT) {
            read();

            not = true;
        }

        e = XreadBooleanTestOrNull();

        if (e == null) {
            return null;
        }

        if (not) {
            e = new ExpressionLogical(OpTypes.NOT, e);
        }

        return e;
    }
