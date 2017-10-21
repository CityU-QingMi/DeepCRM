    protected Object findValue(String expr, Class toType) {
        if (ComponentUtils.altSyntax(getStack()) && toType == String.class) {
        	return TextParseUtil.translateVariables('%', expr, getStack());
            //return translateVariables(expr, getStack());
        } else {
        	expr = ComponentUtils.stripExpressionIfAltSyntax(getStack(), expr);

            return getStack().findValue(expr, toType);
        }
    }
