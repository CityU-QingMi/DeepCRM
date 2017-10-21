    protected void createFromAnnotations(Map<String, ResultConfig> resultConfigs,
            String resultPath, PackageConfig packageConfig, Result[] results,
            Class<?> actionClass, Map<String, ResultTypeConfig> resultsByExtension) {
        // Check for multiple results on the class
        for (Result result : results) {
            for (String name : result.name()) {
                ResultConfig config = createResultConfig(actionClass, new ResultInfo(
                        name, result, packageConfig, resultPath, actionClass,
                        resultsByExtension), packageConfig, result);
                if (config != null) {
                    resultConfigs.put(config.getName(), config);
                }
            }
        }
    }
