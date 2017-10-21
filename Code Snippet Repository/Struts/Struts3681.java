    public static ResultConfig getResultConfig(String namespace, String actionName,
                                               String resultName) {
        ResultConfig result = null;
        ActionConfig actionConfig = getActionConfig(namespace, actionName);
        if (actionConfig != null) {
            Map resultMap = actionConfig.getResults();
            result = (ResultConfig) resultMap.get(resultName);
        }
        return result;
    }
