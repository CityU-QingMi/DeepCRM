    private Object tryFindValue(String expr, Class asType) throws OgnlException {
        Object value = null;
        try {
            expr = lookupForOverrides(expr);
            value = getValue(expr, asType);
            if (value == null) {
                value = findInContext(expr);
                return converter.convertValue(getContext(), value, asType);
            }
        } finally {
            context.remove(THROW_EXCEPTION_ON_FAILURE);
        }
        return value;
    }
