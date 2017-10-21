    public Object findValue(String expr, Class asType, boolean throwExceptionOnFailure) {
        try {
            setupExceptionOnFailure(throwExceptionOnFailure);
            return tryFindValueWhenExpressionIsNotNull(expr, asType);
        } catch (OgnlException e) {
            final Object value = handleOgnlException(expr, throwExceptionOnFailure, e);
            return converter.convertValue(getContext(), value, asType);
        } catch (Exception e) {
            final Object value = handleOtherException(expr, throwExceptionOnFailure, e);
            return converter.convertValue(getContext(), value, asType);
        } finally {
            ReflectionContextState.clear(context);
        }
    }
