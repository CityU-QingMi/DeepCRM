    public Object findValue(String expr, boolean throwExceptionOnFailure) {
        try {
            setupExceptionOnFailure(throwExceptionOnFailure);
            return tryFindValueWhenExpressionIsNotNull(expr);
        } catch (OgnlException e) {
            return handleOgnlException(expr, throwExceptionOnFailure, e);
        } catch (Exception e) {
            return handleOtherException(expr, throwExceptionOnFailure, e);
        } finally {
            ReflectionContextState.clear(context);
        }
    }
