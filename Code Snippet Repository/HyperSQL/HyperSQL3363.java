    Expression XreadBooleanTermOrNull() {

        Expression e = XreadBooleanFactorOrNull();

        if (e == null) {
            return null;
        }

        int type;

        while (true) {
            if (token.tokenType == Tokens.AND) {
                type = OpTypes.AND;
            } else {
                break;
            }

            read();

            Expression a = e;

            e = XreadBooleanFactorOrNull();

            if (e == null) {
                throw unexpectedToken();
            }

            e = new ExpressionLogical(type, a, e);
        }

        return e;
    }
