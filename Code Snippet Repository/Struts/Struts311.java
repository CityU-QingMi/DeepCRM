    public void setValue(String expr, Object value, boolean throwExceptionOnFailure) {
        Map<String, Object> context = getContext();
        try {
            trySetValue(expr, value, throwExceptionOnFailure, context);
        } catch (OgnlException e) {
            handleOgnlException(expr, value, throwExceptionOnFailure, e);
        } catch (RuntimeException re) { //XW-281
            handleRuntimeException(expr, value, throwExceptionOnFailure, re);
        } finally {
            cleanUpContext(context);
        }
    }
