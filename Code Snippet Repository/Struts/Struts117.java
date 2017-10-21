    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof ExceptionMappingConfig)) {
            return false;
        }

        final ExceptionMappingConfig exceptionMappingConfig = (ExceptionMappingConfig) o;

        if ((name != null) ? (!name.equals(exceptionMappingConfig.name)) : (exceptionMappingConfig.name != null)) {
            return false;
        }

        if ((exceptionClassName != null) ? (!exceptionClassName.equals(exceptionMappingConfig.exceptionClassName)) : (exceptionMappingConfig.exceptionClassName != null))
        {
            return false;
        }

        if ((result != null) ? (!result.equals(exceptionMappingConfig.result)) : (exceptionMappingConfig.result != null))
        {
            return false;
        }

        if ((params != null) ? (!params.equals(exceptionMappingConfig.params)) : (exceptionMappingConfig.params != null))
        {
            return false;
        }

        return true;
    }
