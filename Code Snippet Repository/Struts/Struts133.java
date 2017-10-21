    public List<ExceptionMappingConfig> getAllExceptionMappingConfigs() {
        List<ExceptionMappingConfig> allExceptionMappings = new ArrayList<>();

        if (!parents.isEmpty()) {
            for (PackageConfig parentContext : parents) {
                allExceptionMappings.addAll(parentContext.getAllExceptionMappingConfigs());
            }
        }

        allExceptionMappings.addAll(getGlobalExceptionMappingConfigs());

        return allExceptionMappings;
    }
