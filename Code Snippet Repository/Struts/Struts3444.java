    public String intercept(ActionInvocation invocation) throws Exception {
        if (bundleContext != null) {
            Object action = invocation.getAction();

            //inject BundleContext
            if (action instanceof BundleContextAware)
                ((BundleContextAware)action).setBundleContext(bundleContext);

            //inject service implementations
            if (action instanceof ServiceAware) {
                Type[] types = action.getClass().getGenericInterfaces();
                if (types != null) {
                    for (Type type : types) {
                        if (type instanceof ParameterizedType) {
                            ParameterizedType parameterizedType = (ParameterizedType) type;
                            if (parameterizedType.getRawType() instanceof Class) {
                                Class clazz = (Class) parameterizedType.getRawType();
                                if (ServiceAware.class.equals(clazz)) {
                                    Class serviceClass = (Class) parameterizedType.getActualTypeArguments()[0];
                                    ServiceReference[] refs = bundleContext.getAllServiceReferences(serviceClass.getName(), null);
                                    //get the services
                                    if (refs != null) {
                                        List services = new ArrayList(refs.length);
                                        for (ServiceReference ref : refs) {
                                            Object service = bundleContext.getService(ref);
                                            //wow, that's a lot of nested ifs
                                            if (service != null)
                                                services.add(service);
                                        }

                                        if (!services.isEmpty())
                                            ((ServiceAware)action).setServices(services);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else if (LOG.isWarnEnabled()){
            LOG.warn("The OSGi interceptor was not able to find the BundleContext in the ServletContext");          
        }

        return invocation.invoke();
    }
