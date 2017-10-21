    public Interceptor buildInterceptor(InterceptorConfig interceptorConfig, Map interceptorRefParams)
            throws ConfigurationException {
        String interceptorClassName = interceptorConfig.getClassName();
        Map thisInterceptorClassParams = interceptorConfig.getParams();
        Map params = (thisInterceptorClassParams == null) ? new HashMap() : new HashMap(thisInterceptorClassParams);
        params.putAll(interceptorRefParams);

        String message;
        Throwable cause;

        try {
            Map extraContext = new HashMap();
            extraContext.put(PLEXUS_COMPONENT_TYPE, Interceptor.class.getName());
            Interceptor interceptor = (Interceptor) buildBean(interceptorClassName, extraContext);
            reflectionProvider.setProperties(params, interceptor);
            interceptor.init();

            return interceptor;
        }
        catch (InstantiationException e) {
            cause = e;
            message = "Unable to instantiate an instance of Interceptor class [" + interceptorClassName + "].";
        }
        catch (IllegalAccessException e) {
            cause = e;
            message = "IllegalAccessException while attempting to instantiate an instance of Interceptor class [" + interceptorClassName + "].";
        }
        catch (ClassCastException e) {
            cause = e;
            message = "Class [" + interceptorClassName + "] does not implement com.opensymphony.xwork2.interceptor.Interceptor";
        }
        catch (Exception e) {
            cause = e;
            message = "Caught Exception while registering Interceptor class " + interceptorClassName;
        }
        catch (NoClassDefFoundError e) {
            cause = e;
            message = "Could not load class " + interceptorClassName + ". Perhaps it exists but certain dependencies are not available?";
        }

        throw new ConfigurationException(message, cause);
    }
