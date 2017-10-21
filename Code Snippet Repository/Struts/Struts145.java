    private ActionConfig buildFullActionConfig(PackageConfig packageContext, ActionConfig baseConfig) throws ConfigurationException {
        Map<String, String> params = new TreeMap<>(baseConfig.getParams());
        Map<String, ResultConfig> results = new TreeMap<>();

        if (!baseConfig.getPackageName().equals(packageContext.getName()) && packageContexts.containsKey(baseConfig.getPackageName())) {
            results.putAll(packageContexts.get(baseConfig.getPackageName()).getAllGlobalResults());
        } else {
            results.putAll(packageContext.getAllGlobalResults());
        }

       	results.putAll(baseConfig.getResults());

        setDefaultResults(results, packageContext);

        List<InterceptorMapping> interceptors = new ArrayList<>(baseConfig.getInterceptors());

        if (interceptors.size() <= 0) {
            String defaultInterceptorRefName = packageContext.getFullDefaultInterceptorRef();

            if (defaultInterceptorRefName != null) {
                interceptors.addAll(InterceptorBuilder.constructInterceptorReference(new PackageConfig.Builder(packageContext), defaultInterceptorRefName,
                        new LinkedHashMap<String, String>(), packageContext.getLocation(), objectFactory));
            }
        }

        String methodRegex = container.getInstance(String.class, StrutsConstants.STRUTS_SMI_METHOD_REGEX);
        if (methodRegex == null) {
            methodRegex = ActionConfig.DEFAULT_METHOD_REGEX;
        }

        LOG.debug("Using pattern [{}] to match allowed methods when SMI is disabled!", methodRegex);

        return new ActionConfig.Builder(baseConfig)
            .addParams(params)
            .addResultConfigs(results)
            .defaultClassName(packageContext.getDefaultClassRef())  // fill in default if non class has been provided
            .interceptors(interceptors)
            .setStrictMethodInvocation(packageContext.isStrictMethodInvocation())
            .setDefaultMethodRegex(methodRegex)
            .addExceptionMappings(packageContext.getAllExceptionMappingConfigs())
            .build();
    }
