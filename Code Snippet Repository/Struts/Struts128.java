    protected PackageConfig(PackageConfig orig) {
        this.defaultInterceptorRef = orig.defaultInterceptorRef;
        this.defaultActionRef = orig.defaultActionRef;
        this.defaultResultType = orig.defaultResultType;
        this.defaultClassRef = orig.defaultClassRef;
        this.name = orig.name;
        this.namespace = orig.namespace;
        this.isAbstract = orig.isAbstract;
        this.needsRefresh = orig.needsRefresh;
        this.actionConfigs = new LinkedHashMap<>(orig.actionConfigs);
        this.globalResultConfigs = new LinkedHashMap<>(orig.globalResultConfigs);
        this.globalAllowedMethods = new LinkedHashSet<>(orig.globalAllowedMethods);
        this.interceptorConfigs = new LinkedHashMap<>(orig.interceptorConfigs);
        this.resultTypeConfigs = new LinkedHashMap<>(orig.resultTypeConfigs);
        this.globalExceptionMappingConfigs = new ArrayList<>(orig.globalExceptionMappingConfigs);
        this.parents = new ArrayList<>(orig.parents);
        this.location = orig.location;
        this.strictMethodInvocation = orig.strictMethodInvocation;
    }
