    private Expression readValue(Expression e, int opType) {

        Expression r = XreadValueExpression();

        if (e == null) {
            return r;
        }

        Expression l = new ExpressionLogical(opType, e, r);
        Expression a = new ExpressionOp(OpTypes.ALTERNATIVE, e, r);

        return new ExpressionOp(OpTypes.CASEWHEN, l, a);
    }
