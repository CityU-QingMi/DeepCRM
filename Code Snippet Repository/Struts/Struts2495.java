    protected List<ExceptionMappingConfig> buildExceptionMappings(ExceptionMapping[] exceptions, String actionName) {
        List<ExceptionMappingConfig> exceptionMappings = new ArrayList<>();

        for (ExceptionMapping exceptionMapping : exceptions) {
            LOG.trace("Mapping exception [{}] to result [{}] for action [{}]", exceptionMapping.exception(),
                        exceptionMapping.result(), actionName);
            ExceptionMappingConfig.Builder builder = new ExceptionMappingConfig.Builder(null, exceptionMapping
                    .exception(), exceptionMapping.result());
            if (exceptionMapping.params() != null)
                builder.addParams(StringTools.createParameterMap(exceptionMapping.params()));
            exceptionMappings.add(builder.build());
        }

        return exceptionMappings;
    }
