    protected void addResult(Class<?> actionClass, String path, Map<String, ResultConfig> results,
                           PackageConfig packageConfig, Map<String, ResultTypeConfig> resultsByExtension,
                           String resultKey) {

        if (!results.containsKey(resultKey)) {
            Map<String, ResultConfig> globalResults = packageConfig.getAllGlobalResults();
            if (globalResults.containsKey(resultKey)) {
                results.put(resultKey, globalResults.get(resultKey));
            } else {
                ResultConfig resultConfig = createResultConfig(actionClass,
                        new ResultInfo(resultKey, path, packageConfig, resultsByExtension),
                        packageConfig, null);
                results.put(resultKey, resultConfig);
            }
        }
    }
