    private Expression readNullIfExpression() {

        read();
        readThis(Tokens.OPENBRACKET);

        Expression c = XreadValueExpression();

        readThis(Tokens.COMMA);

        Expression alternative = new ExpressionOp(OpTypes.ALTERNATIVE,
            new ExpressionValue((Object) null, (Type) null), c);

        c = new ExpressionLogical(c, XreadValueExpression());
        c = new ExpressionOp(OpTypes.CASEWHEN, c, alternative);

        readThis(Tokens.CLOSEBRACKET);

        return c;
    }
