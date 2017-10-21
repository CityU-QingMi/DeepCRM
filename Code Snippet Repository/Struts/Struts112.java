    protected ActionConfig(ActionConfig orig) {
        this.name = orig.name;
        this.className = orig.className;
        this.methodName = orig.methodName;
        this.packageName = orig.packageName;
        this.params = new LinkedHashMap<>(orig.params);
        this.interceptors = new ArrayList<>(orig.interceptors);
        this.results = new LinkedHashMap<>(orig.results);
        this.exceptionMappings = new ArrayList<>(orig.exceptionMappings);
        this.allowedMethods = orig.allowedMethods;
        this.location = orig.location;
    }
