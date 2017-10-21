    public static List<InterceptorMapping> constructInterceptorReference(InterceptorLocator interceptorLocator,
                                                                         String refName, Map<String,String> refParams, Location location, ObjectFactory objectFactory) throws ConfigurationException {
        Object referencedConfig = interceptorLocator.getInterceptorConfig(refName);
        List<InterceptorMapping> result = new ArrayList<>();

        if (referencedConfig == null) {
            throw new ConfigurationException("Unable to find interceptor class referenced by ref-name " + refName, location);
        } else {
            if (referencedConfig instanceof InterceptorConfig) {
                InterceptorConfig config = (InterceptorConfig) referencedConfig;
                Interceptor inter;
                try {
                    inter = objectFactory.buildInterceptor(config, refParams);
                    result.add(new InterceptorMapping(refName, inter, refParams));
                } catch (ConfigurationException ex) {
              	    LOG.warn(new ParameterizedMessage("Unable to load config class {} at {} probably due to a missing jar, which might be fine if you never plan to use the {} interceptor",
                            config.getClassName(), ex.getLocation(), config.getName()), ex);
                }

            } else if (referencedConfig instanceof InterceptorStackConfig) {
                InterceptorStackConfig stackConfig = (InterceptorStackConfig) referencedConfig;

                if ((refParams != null) && (refParams.size() > 0)) {
                    result = constructParameterizedInterceptorReferences(interceptorLocator, stackConfig, refParams, objectFactory);
                } else {
                    result.addAll(stackConfig.getInterceptors());
                }

            } else {
                LOG.error("Got unexpected type for interceptor {}. Got {}", refName, referencedConfig);
            }
        }

        return result;
    }
