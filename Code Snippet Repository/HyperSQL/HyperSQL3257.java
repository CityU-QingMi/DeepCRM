    Expression XreadValueExpressionWithContext() {

        Expression e;

        compileContext.contextuallyTypedExpression = true;
        e = XreadValueExpressionOrNull();
        compileContext.contextuallyTypedExpression = false;

        return e;
    }
