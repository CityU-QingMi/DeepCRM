    @Override
    public int hashCode() {
        int result = actionConfigs != null ? actionConfigs.hashCode() : 0;
        result = 31 * result + (globalResultConfigs != null ? globalResultConfigs.hashCode() : 0);
        result = 31 * result + (globalAllowedMethods != null ? globalAllowedMethods.hashCode() : 0);
        result = 31 * result + (interceptorConfigs != null ? interceptorConfigs.hashCode() : 0);
        result = 31 * result + (resultTypeConfigs != null ? resultTypeConfigs.hashCode() : 0);
        result = 31 * result + (globalExceptionMappingConfigs != null ? globalExceptionMappingConfigs.hashCode() : 0);
        result = 31 * result + (parents != null ? parents.hashCode() : 0);
        result = 31 * result + (defaultInterceptorRef != null ? defaultInterceptorRef.hashCode() : 0);
        result = 31 * result + (defaultActionRef != null ? defaultActionRef.hashCode() : 0);
        result = 31 * result + (defaultResultType != null ? defaultResultType.hashCode() : 0);
        result = 31 * result + (defaultClassRef != null ? defaultClassRef.hashCode() : 0);
        result = 31 * result + name.hashCode();
        result = 31 * result + (namespace != null ? namespace.hashCode() : 0);
        result = 31 * result + (isAbstract ? 1 : 0);
        result = 31 * result + (needsRefresh ? 1 : 0);
        result = 31 * result + (strictMethodInvocation ? 1 : 0);
        return result;
    }
