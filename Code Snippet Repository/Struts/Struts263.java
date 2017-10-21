    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        String result;

        try {
            result = invocation.invoke();
        } catch (Exception e) {
            if (isLogEnabled()) {
                handleLogging(e);
            }
            List<ExceptionMappingConfig> exceptionMappings = invocation.getProxy().getConfig().getExceptionMappings();
            ExceptionMappingConfig mappingConfig = this.findMappingFromExceptions(exceptionMappings, e);
            if (mappingConfig != null && mappingConfig.getResult()!=null) {
                Map<String, String> mappingParams = mappingConfig.getParams();
                // create a mutable HashMap since some interceptors will remove parameters, and parameterMap is immutable
                HttpParameters parameters = HttpParameters.create(mappingParams).build();
                invocation.getInvocationContext().setParameters(parameters);
                result = mappingConfig.getResult();
                publishException(invocation, new ExceptionHolder(e));
            } else {
                throw e;
            }
        }

        return result;
    }
