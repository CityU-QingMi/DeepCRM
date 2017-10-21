    protected ActionConfig buildActionConfig(String path, ResultTypeConfig resultTypeConfig) {
        Map<String, ResultConfig> results = new HashMap<>();
        HashMap<String, String> params = new HashMap<>();
        if (resultTypeConfig.getParams() != null) {
            params.putAll(resultTypeConfig.getParams());
        }
        params.put(resultTypeConfig.getDefaultResultParam(), path);

        PackageConfig pkg = configuration.getPackageConfig(defaultParentPackageName);
        List<InterceptorMapping> interceptors = InterceptorBuilder.constructInterceptorReference(pkg, pkg.getFullDefaultInterceptorRef(), Collections.<String, String>emptyMap(), null, objectFactory);
        ResultConfig config = new ResultConfig.Builder(Action.SUCCESS, resultTypeConfig.getClassName()).
                addParams(params).build();
        results.put(Action.SUCCESS, config);

        return new ActionConfig.Builder(defaultParentPackageName, "execute", ActionSupport.class.getName()).
                addInterceptors(interceptors).
                addResultConfigs(results).
                addAllowedMethod(pkg.getGlobalAllowedMethods()).
                build();
    }
