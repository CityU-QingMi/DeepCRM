    protected void createResultsFromAnnotations(Class<?> actionClass, PackageConfig packageConfig, String defaultResultPath,
                                                Map<String, ResultConfig> results, Map<String, ResultTypeConfig> resultsByExtension) {
        Results resultsAnn = actionClass.getAnnotation(Results.class);
        if (resultsAnn != null) {
            createFromAnnotations(results, defaultResultPath, packageConfig, resultsAnn.value(),
                    actionClass, resultsByExtension);
        }

        Result resultAnn = actionClass.getAnnotation(Result.class);
        if (resultAnn != null) {
            createFromAnnotations(results, defaultResultPath, packageConfig, new Result[]{resultAnn},
                    actionClass, resultsByExtension);
        }
    }
