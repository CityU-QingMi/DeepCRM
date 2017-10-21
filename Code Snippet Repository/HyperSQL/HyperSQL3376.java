    Expression XreadRowValueSpecialCase() {

        Expression e = XreadSimpleValueExpressionPrimary();

        if (e != null) {
            e = XreadArrayElementReference(e);
        }

        return e;
    }
