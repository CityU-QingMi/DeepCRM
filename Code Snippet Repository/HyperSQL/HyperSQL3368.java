    Expression XreadPredicate() {

        switch (token.tokenType) {

            case Tokens.EXISTS : {
                read();

                Expression s = XreadTableSubquery(OpTypes.EXISTS);

                return new ExpressionLogical(OpTypes.EXISTS, s);
            }
            case Tokens.UNIQUE : {
                read();

                Expression s = XreadTableSubquery(OpTypes.UNIQUE);

                return new ExpressionLogical(OpTypes.UNIQUE, s);
            }
            default : {
                Expression a = XreadRowValuePredicand();

                return XreadPredicateRightPart(a);
            }
        }
    }
