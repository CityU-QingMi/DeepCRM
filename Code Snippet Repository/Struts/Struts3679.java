    public static Set<String> getActionNames(String namespace) {
        Set<String> actionNames = Collections.emptySet();
        Map<String, Map<String, ActionConfig>> allActionConfigs = getActionConfigs();
        if (allActionConfigs != null) {
            Map<String, ActionConfig> actionMappings = allActionConfigs.get(namespace);
            if (actionMappings != null) {
                actionNames = actionMappings.keySet();
            }
        }
        return actionNames;
    }
