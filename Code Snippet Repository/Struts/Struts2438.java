    public Set<String> getActionNames(String namespace) {
        Set<String> actionNames = Collections.emptySet();
        Map<String, Map<String, ActionConfig>> allActionConfigs = configuration.getRuntimeConfiguration().getActionConfigs();
        if (allActionConfigs != null) {
            Map<String, ActionConfig> actionMappings = allActionConfigs.get(namespace);
            if (actionMappings != null) {
                actionNames = actionMappings.keySet();
            }
        }
        return actionNames;
    }
