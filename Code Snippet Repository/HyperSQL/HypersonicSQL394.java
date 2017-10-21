    Expression XreadValueExpressionOrNull() {

        Expression e = XreadAllTypesCommonValueExpression(true);

        if (e == null) {
            return null;
        }

        return e;
    }
