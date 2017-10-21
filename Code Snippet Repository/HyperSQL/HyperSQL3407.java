    private Expression readConcatSeparatorExpressionOrNull() {

        HsqlArrayList array    = new HsqlArrayList();
        int           position = getPosition();

        read();

        if (!readIfThis(Tokens.OPENBRACKET)) {
            rewind(position);

            return null;
        }

        Expression e = XreadValueExpression();

        array.add(e);
        readThis(Tokens.COMMA);

        e = XreadValueExpression();

        array.add(e);
        readThis(Tokens.COMMA);

        do {
            e = XreadValueExpression();

            array.add(e);

            if (token.tokenType == Tokens.COMMA) {
                readThis(Tokens.COMMA);
            } else if (token.tokenType == Tokens.CLOSEBRACKET) {
                readThis(Tokens.CLOSEBRACKET);

                break;
            }
        } while (true);

        Expression[] expressions = new Expression[array.size()];

        array.toArray(expressions);

        return new ExpressionOp(OpTypes.CONCAT_WS, expressions);
    }
