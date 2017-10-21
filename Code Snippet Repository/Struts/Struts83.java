    protected String saveResult(ActionConfig actionConfig, Object methodResult) {
        if (methodResult instanceof Result) {
            this.explicitResult = (Result) methodResult;

            // Wire the result automatically
            container.inject(explicitResult);
            return null;
        } else {
            return (String) methodResult;
        }
    }
