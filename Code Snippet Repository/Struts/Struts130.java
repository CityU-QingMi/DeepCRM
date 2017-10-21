    public Map<String, ResultConfig> getAllGlobalResults() {
        Map<String, ResultConfig> retMap = new LinkedHashMap<>();

        if (!parents.isEmpty()) {
            for (PackageConfig parentConfig : parents) {
                retMap.putAll(parentConfig.getAllGlobalResults());
            }
        }

        retMap.putAll(getGlobalResultConfigs());

        return retMap;
    }
