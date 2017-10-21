    @SuppressWarnings(value = {""})
    protected ResultConfig createResultConfig(Class<?> actionClass, ResultInfo info,
            PackageConfig packageConfig, Result result) {
        // Look up by the type that was determined from the annotation or by the extension in the
        // ResultInfo class
        ResultTypeConfig resultTypeConfig = packageConfig.getAllResultTypeConfigs().get(info.type);
        if (resultTypeConfig == null) {
            throw new ConfigurationException("The Result type [" + info.type + "] which is" +
                " defined in the Result annotation on the class [" + actionClass + "] or determined" +
                " by the file extension or is the default result type for the PackageConfig of the" +
                " action, could not be found as a result-type defined for the Struts/XWork package [" +
                packageConfig.getName() + "]");
        }

        // Add the default parameters for the result type config (if any)
        HashMap<String, String> params = new HashMap<>();
        if (resultTypeConfig.getParams() != null) {
            params.putAll(resultTypeConfig.getParams());
        }

        // Handle the annotation
        if (result != null) {
            params.putAll(StringTools.createParameterMap(result.params()));
        }

        // Map the location to the default param for the result or a param named location
        if (info.location != null) {
            String defaultParamName = resultTypeConfig.getDefaultResultParam();
            if (!params.containsKey(defaultParamName)) {
                params.put(defaultParamName, info.location);
            }
        }

        return new ResultConfig.Builder(info.name, resultTypeConfig.getClassName()).addParams(params).build();
    }
