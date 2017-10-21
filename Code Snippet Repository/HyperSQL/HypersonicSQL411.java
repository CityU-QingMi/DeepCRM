    Expression XreadBooleanTestOrNull() {

        boolean    unknown = false;
        boolean    isNot   = false;
        Expression e       = XreadBooleanPrimaryOrNull();

        if (e == null) {
            return e;
        }

        if (token.tokenType == Tokens.IS) {
            read();

            if (token.tokenType == Tokens.NOT) {
                read();

                isNot = true;
            }

            if (token.tokenType == Tokens.TRUE) {
                read();
            } else if (token.tokenType == Tokens.FALSE) {
                read();

                isNot = !isNot;
            } else if (token.tokenType == Tokens.UNKNOWN) {
                read();

                unknown = true;
            } else {
                throw unexpectedToken();
            }
        }

        if (unknown) {
            e = new ExpressionLogical(OpTypes.IS_NULL, e);
        }

        if (isNot) {
            e = new ExpressionLogical(OpTypes.NOT, e);
        }

        return e;
    }
