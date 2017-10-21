    protected void handleRuntimeException(String expr, Object value, boolean throwExceptionOnFailure, RuntimeException re) {
        if (throwExceptionOnFailure) {
            String message = ErrorMessageBuilder.create()
                    .errorSettingExpressionWithValue(expr, value)
                    .build();
            throw new XWorkException(message, re);
        } else {
            LOG.warn("Error setting value [{}] with expression [{}]", value, expr, re);
        }
    }
