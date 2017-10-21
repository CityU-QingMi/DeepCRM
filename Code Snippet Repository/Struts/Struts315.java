    private Object tryFindValue(String expr) throws OgnlException {
        Object value;
        expr = lookupForOverrides(expr);
        if (defaultType != null) {
            value = findValue(expr, defaultType);
        } else {
            value = getValueUsingOgnl(expr);
            if (value == null) {
                value = findInContext(expr);
            }
        }
        return value;
    }
