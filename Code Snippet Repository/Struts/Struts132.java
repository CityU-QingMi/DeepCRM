    public Map<String, ResultTypeConfig> getAllResultTypeConfigs() {
        Map<String, ResultTypeConfig> retMap = new LinkedHashMap<>();

        if (!parents.isEmpty()) {
            for (PackageConfig parentContext : parents) {
                retMap.putAll(parentContext.getAllResultTypeConfigs());
            }
        }

        retMap.putAll(getResultTypeConfigs());

        return retMap;
    }
