    protected PackageConfig(String name) {
        this.name = name;
        actionConfigs = new LinkedHashMap<>();
        globalResultConfigs = new LinkedHashMap<>();
        globalAllowedMethods = new HashSet<>();
        interceptorConfigs = new LinkedHashMap<>();
        resultTypeConfigs = new LinkedHashMap<>();
        globalExceptionMappingConfigs = new ArrayList<>();
        parents = new ArrayList<>();
    }
