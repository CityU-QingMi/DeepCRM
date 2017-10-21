    public Map<String, Object> getAllInterceptorConfigs() {
        Map<String, Object> retMap = new LinkedHashMap<>();

        if (!parents.isEmpty()) {
            for (PackageConfig parentContext : parents) {
                retMap.putAll(parentContext.getAllInterceptorConfigs());
            }
        }

        retMap.putAll(getInterceptorConfigs());

        return retMap;
    }
