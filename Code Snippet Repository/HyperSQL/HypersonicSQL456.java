    private Expression readIfNull2ExpressionOrNull() {

        int position = getPosition();

        read();

        if (!readIfThis(Tokens.OPENBRACKET)) {
            rewind(position);

            return null;
        }

        Expression c = XreadValueExpression();

        readThis(Tokens.COMMA);

        Expression e1 = XreadValueExpression();

        readThis(Tokens.COMMA);

        Expression e2        = XreadValueExpression();
        Expression condition = new ExpressionLogical(OpTypes.IS_NULL, c);
        Expression alt       = new ExpressionOp(OpTypes.ALTERNATIVE, e2, e1);

        c = new ExpressionOp(OpTypes.CASEWHEN, condition, alt);

        c.setSubType(OpTypes.CAST);
        alt.setSubType(OpTypes.CAST);
        readThis(Tokens.CLOSEBRACKET);

        return c;
    }
