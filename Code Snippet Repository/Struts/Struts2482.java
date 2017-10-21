    public Map<String, ResultConfig> build(Class<?> actionClass,
        org.apache.struts2.convention.annotation.Action annotation, String actionName,
            PackageConfig packageConfig) {

        // Get the default result location from the annotation or configuration
        String defaultResultPath = conventionsService.determineResultPath(actionClass);

        // Add a slash
        if (!defaultResultPath.endsWith("/")) {
            defaultResultPath = defaultResultPath + "/";
        }

        // Check for resources with the action name
        final String namespace = packageConfig.getNamespace();
        if (namespace != null && namespace.startsWith("/")) {
             defaultResultPath = defaultResultPath + namespace.substring(1);
        } else if (namespace != null) {
            defaultResultPath = defaultResultPath + namespace;
        }

        if (LOG.isTraceEnabled()) {
            LOG.trace("Using final calculated namespace [{}]", namespace);
        }

        // Add that ending slash for concatenation
        if (!defaultResultPath.endsWith("/")) {
            defaultResultPath += "/";
        }

        String resultPrefix = defaultResultPath + actionName;

        //results from files
        Map<String, ResultConfig> results = new HashMap<>();
        Map<String, ResultTypeConfig> resultsByExtension = conventionsService.getResultTypesByExtension(packageConfig);
        createFromResources(actionClass, results, defaultResultPath, resultPrefix, actionName,
            packageConfig, resultsByExtension);

        //get inherited @Results and @Result (class level)
        for (Class<?> clazz : ReflectionTools.getClassHierarchy(actionClass)) {
            createResultsFromAnnotations(clazz, packageConfig, defaultResultPath, results, resultsByExtension);
        }


        //method level
        if (annotation != null && annotation.results() != null && annotation.results().length > 0) {
            createFromAnnotations(results, defaultResultPath, packageConfig, annotation.results(),
                    actionClass, resultsByExtension);
        }
        return results;
    }
