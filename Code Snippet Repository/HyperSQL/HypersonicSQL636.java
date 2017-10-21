    static Expression decomposeOrConditions(Expression e,
            HsqlList conditions) {

        if (e == null) {
            return Expression.EXPR_FALSE;
        }

        Expression arg1 = e.getLeftNode();
        Expression arg2 = e.getRightNode();
        int        type = e.getType();

        if (type == OpTypes.OR) {
            arg1 = decomposeOrConditions(arg1, conditions);
            arg2 = decomposeOrConditions(arg2, conditions);

            if (arg1.isFalse()) {
                return arg2;
            }

            if (arg2.isFalse()) {
                return arg1;
            }

            e = new ExpressionLogical(OpTypes.OR, arg1, arg2);

            return e;
        }

        if (!e.isFalse()) {
            conditions.add(e);
        }

        return Expression.EXPR_FALSE;
    }
