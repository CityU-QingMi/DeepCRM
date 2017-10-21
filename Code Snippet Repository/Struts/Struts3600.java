    @Override
    protected String saveResult(ActionConfig actionConfig, Object methodResult) {
        if (methodResult instanceof Result) {
            explicitResult = (Result) methodResult;
            // Wire the result automatically
            container.inject(explicitResult);
        } else if (methodResult instanceof HttpHeaders) {
            httpHeaders = (HttpHeaders) methodResult;
            resultCode = httpHeaders.getResultCode();
        } else if (methodResult instanceof String) {
            resultCode = (String) methodResult;
        } else if (methodResult != null) {
            throw new ConfigurationException("The result type " + methodResult.getClass()
                    + " is not allowed. Use the type String, HttpHeaders or Result.");
        }
        return resultCode;
    }
