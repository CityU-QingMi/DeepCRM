    static Expression decomposeAndConditions(Session session, Expression e,
            HsqlList conditions) {

        if (e == null) {
            return Expression.EXPR_TRUE;
        }

        Expression arg1 = e.getLeftNode();
        Expression arg2 = e.getRightNode();
        int        type = e.getType();

        if (type == OpTypes.AND) {
            arg1 = decomposeAndConditions(session, arg1, conditions);
            arg2 = decomposeAndConditions(session, arg2, conditions);

            if (arg1.isTrue()) {
                return arg2;
            }

            if (arg2.isTrue()) {
                return arg1;
            }

            e.setLeftNode(arg1);
            e.setRightNode(arg2);

            return e;
        } else if (type == OpTypes.EQUAL) {
            if (arg1.getType() == OpTypes.ROW
                    && arg2.getType() == OpTypes.ROW) {
                for (int i = 0; i < arg1.nodes.length; i++) {
                    Expression part = new ExpressionLogical(arg1.nodes[i],
                        arg2.nodes[i]);

                    part.resolveTypes(session, null);
                    conditions.add(part);
                }

                return Expression.EXPR_TRUE;
            }
        }

        if (!e.isTrue()) {
            conditions.add(e);
        }

        return Expression.EXPR_TRUE;
    }
