    private Expression readCaseWhenExpressionOrNull() {

        Expression l        = null;
        int        position = getPosition();

        read();

        if (!readIfThis(Tokens.OPENBRACKET)) {
            rewind(position);

            return null;
        }

        l = XreadBooleanValueExpression();

        readThis(Tokens.COMMA);

        Expression then = XreadValueExpression();

        readThis(Tokens.COMMA);

        Expression thenelse = new ExpressionOp(OpTypes.ALTERNATIVE, then,
                                               XreadValueExpression());

        l = new ExpressionOp(OpTypes.CASEWHEN, l, thenelse);

        readThis(Tokens.CLOSEBRACKET);

        return l;
    }
