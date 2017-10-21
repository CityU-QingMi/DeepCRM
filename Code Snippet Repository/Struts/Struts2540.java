    private PackageConfig makePackageConfig(String name, String namespace, PackageConfig parent,
            String defaultResultType, ResultTypeConfig[] results, List<InterceptorConfig> interceptors,
            List<InterceptorStackConfig> interceptorStacks, Set<String> globalAllowedMethods) {
        PackageConfig.Builder builder = new PackageConfig.Builder(name);
        if (namespace != null) {
            builder.namespace(namespace);
        }
        if (parent != null) {
            builder.addParent(parent);
        }
        if (defaultResultType != null) {
            builder.defaultResultType(defaultResultType);
        }
        if (results != null) {
            for (ResultTypeConfig result : results) {
                builder.addResultTypeConfig(result);
            }
        }
        if (interceptors != null) {
            for (InterceptorConfig ref : interceptors) {
                builder.addInterceptorConfig(ref);
            }
        }
        if (interceptorStacks != null) {
            for (InterceptorStackConfig ref : interceptorStacks) {
                builder.addInterceptorStackConfig(ref);
            }
        }

        if (globalAllowedMethods != null) {
            builder.addGlobalAllowedMethods(globalAllowedMethods);
        }

        return new MyPackageConfig(builder.build());
    }
