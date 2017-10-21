    protected Object findValue(String expr, Class toType) {
        if (altSyntax() && toType == String.class) {
            if (ComponentUtils.containsExpression(expr)) {
                return TextParseUtil.translateVariables('%', expr, stack);
            } else {
                return expr;
            }
        } else {
            expr = stripExpressionIfAltSyntax(expr);

            return getStack().findValue(expr, toType, throwExceptionOnELFailure);
        }
    }
