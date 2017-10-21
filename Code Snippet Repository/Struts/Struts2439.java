    public ActionConfig getActionConfig(String namespace, String actionName) {
        ActionConfig config = null;
        Map<String, Map<String, ActionConfig>> allActionConfigs = configuration.getRuntimeConfiguration().getActionConfigs();
        if (allActionConfigs != null) {
            Map<String, ActionConfig> actionMappings = allActionConfigs.get(namespace);
            if (actionMappings != null) {
                config = actionMappings.get(actionName);
            }
        }
        return config;
    }
