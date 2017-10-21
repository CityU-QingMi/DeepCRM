    protected void handleOgnlException(String expr, Object value, boolean throwExceptionOnFailure, OgnlException e) {
    	boolean shouldLog = shouldLogMissingPropertyWarning(e);
    	String msg = null;
    	if (throwExceptionOnFailure || shouldLog) {
            msg = ErrorMessageBuilder.create().errorSettingExpressionWithValue(expr, value).build();
        }
        if (shouldLog) {
            LOG.warn(msg, e);
    	}
    	
        if (throwExceptionOnFailure) {
            throw new XWorkException(msg, e);
        }
    }
