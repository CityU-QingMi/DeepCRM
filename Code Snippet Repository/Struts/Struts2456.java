    protected Result buildResult(String path, String resultCode, ResultTypeConfig config, ActionContext invocationContext) {
        String resultClass = config.getClassName();

        Map<String, String> params = new LinkedHashMap<>();
        if (config.getParams() != null) {
            params.putAll(config.getParams());
        }
        params.put(config.getDefaultResultParam(), path);

        ResultConfig resultConfig = new ResultConfig.Builder(resultCode, resultClass).addParams(params).build();
        try {
            return objectFactory.buildResult(resultConfig, invocationContext.getContextMap());
        } catch (Exception e) {
            throw new XWorkException("Unable to build convention result", e, resultConfig);
        }
    }
