    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PackageConfig that = (PackageConfig) o;

        if (isAbstract != that.isAbstract) return false;
        if (needsRefresh != that.needsRefresh) return false;
        if (strictMethodInvocation != that.strictMethodInvocation) return false;
        if (actionConfigs != null ? !actionConfigs.equals(that.actionConfigs) : that.actionConfigs != null)
            return false;
        if (globalResultConfigs != null ? !globalResultConfigs.equals(that.globalResultConfigs) : that.globalResultConfigs != null)
            return false;
        if (globalAllowedMethods != null ? !globalAllowedMethods.equals(that.globalAllowedMethods) : that.globalAllowedMethods != null)
            return false;
        if (interceptorConfigs != null ? !interceptorConfigs.equals(that.interceptorConfigs) : that.interceptorConfigs != null)
            return false;
        if (resultTypeConfigs != null ? !resultTypeConfigs.equals(that.resultTypeConfigs) : that.resultTypeConfigs != null)
            return false;
        if (globalExceptionMappingConfigs != null ? !globalExceptionMappingConfigs.equals(that.globalExceptionMappingConfigs) : that.globalExceptionMappingConfigs != null)
            return false;
        if (parents != null ? !parents.equals(that.parents) : that.parents != null) return false;
        if (defaultInterceptorRef != null ? !defaultInterceptorRef.equals(that.defaultInterceptorRef) : that.defaultInterceptorRef != null)
            return false;
        if (defaultActionRef != null ? !defaultActionRef.equals(that.defaultActionRef) : that.defaultActionRef != null)
            return false;
        if (defaultResultType != null ? !defaultResultType.equals(that.defaultResultType) : that.defaultResultType != null)
            return false;
        if (defaultClassRef != null ? !defaultClassRef.equals(that.defaultClassRef) : that.defaultClassRef != null)
            return false;
        if (!name.equals(that.name)) return false;
        return !(namespace != null ? !namespace.equals(that.namespace) : that.namespace != null);

    }
