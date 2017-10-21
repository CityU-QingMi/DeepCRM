    static Expression orExpressions(Expression e1, Expression e2) {

        if (e1 == null) {
            return e2;
        }

        if (e2 == null) {
            return e1;
        }

        if (e1 == e2) {
            return e1;
        }

        return new ExpressionLogical(OpTypes.OR, e1, e2);
    }
