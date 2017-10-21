    public Map<String, ActionConfig> getAllActionConfigs() {
        Map<String, ActionConfig> retMap = new LinkedHashMap<>();

        if (!parents.isEmpty()) {
            for (PackageConfig parent : parents) {
                retMap.putAll(parent.getAllActionConfigs());
            }
        }

        retMap.putAll(getActionConfigs());

        return retMap;
    }
