    public static Expression getCastExpression(Session session, Expression e,
            Type dataType) {

        if (e.getType() == OpTypes.VALUE) {
            Object value = dataType.castToType(session, e.getValue(session),
                                               e.getDataType());

            return new ExpressionValue(value, dataType);
        }

        return new ExpressionOp(e, dataType);
    }
