        public PackageConfig build() {
            target.actionConfigs = Collections.unmodifiableMap(target.actionConfigs);
            target.globalResultConfigs = Collections.unmodifiableMap(target.globalResultConfigs);
            target.interceptorConfigs = Collections.unmodifiableMap(target.interceptorConfigs);
            target.resultTypeConfigs = Collections.unmodifiableMap(target.resultTypeConfigs);
            target.globalExceptionMappingConfigs = Collections.unmodifiableList(target.globalExceptionMappingConfigs);
            target.parents = Collections.unmodifiableList(target.parents);
            PackageConfig result = target;
            target = new PackageConfig(result);
            return result;
        }
