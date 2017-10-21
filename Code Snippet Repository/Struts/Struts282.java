    protected void addParametersToContext(ActionContext ac, Map<String, ?> newParams) {
        HttpParameters previousParams = ac.getParameters();

        HttpParameters.Builder combinedParams = HttpParameters.create();
        if (overwrite) {
            if (previousParams != null) {
                combinedParams = combinedParams.withParent(previousParams);
            }
            if (newParams != null) {
                combinedParams = combinedParams.withExtraParams(newParams);
            }
        } else {
            if (newParams != null) {
                combinedParams = combinedParams.withExtraParams(newParams);
            }
            if (previousParams != null) {
                combinedParams = combinedParams.withParent(previousParams);
            }
        }
        ac.setParameters(combinedParams.build());
    }
