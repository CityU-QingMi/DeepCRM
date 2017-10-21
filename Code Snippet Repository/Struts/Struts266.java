    protected ExceptionMappingConfig findMappingFromExceptions(List<ExceptionMappingConfig> exceptionMappings, Throwable t) {
    	ExceptionMappingConfig config = null;
        // Check for specific exception mappings.
        if (exceptionMappings != null) {
            int deepest = Integer.MAX_VALUE;
            for (Object exceptionMapping : exceptionMappings) {
                ExceptionMappingConfig exceptionMappingConfig = (ExceptionMappingConfig) exceptionMapping;
                int depth = getDepth(exceptionMappingConfig.getExceptionClassName(), t);
                if (depth >= 0 && depth < deepest) {
                    deepest = depth;
                    config = exceptionMappingConfig;
                }
            }
        }
        return config;
    }
