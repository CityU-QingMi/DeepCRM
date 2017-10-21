    public static ThreadContextMap createThreadContextMap() {
        final PropertiesUtil managerProps = PropertiesUtil.getProperties();
        final String threadContextMapName = managerProps.getStringProperty(THREAD_CONTEXT_KEY);
        final ClassLoader cl = ProviderUtil.findClassLoader();
        ThreadContextMap result = null;
        if (threadContextMapName != null) {
            try {
                final Class<?> clazz = cl.loadClass(threadContextMapName);
                if (ThreadContextMap.class.isAssignableFrom(clazz)) {
                    result = (ThreadContextMap) clazz.newInstance();
                }
            } catch (final ClassNotFoundException cnfe) {
                LOGGER.error("Unable to locate configured ThreadContextMap {}", threadContextMapName);
            } catch (final Exception ex) {
                LOGGER.error("Unable to create configured ThreadContextMap {}", threadContextMapName, ex);
            }
        }
        if (result == null && ProviderUtil.hasProviders() && LogManager.getFactory() != null) { //LOG4J2-1658
            final String factoryClassName = LogManager.getFactory().getClass().getName();
            for (final Provider provider : ProviderUtil.getProviders()) {
                if (factoryClassName.equals(provider.getClassName())) {
                    final Class<? extends ThreadContextMap> clazz = provider.loadThreadContextMap();
                    if (clazz != null) {
                        try {
                            result = clazz.newInstance();
                            break;
                        } catch (final Exception e) {
                            LOGGER.error("Unable to locate or load configured ThreadContextMap {}",
                                    provider.getThreadContextMap(), e);
                            result = createDefaultThreadContextMap();
                        }
                    }
                }
            }
        }
        if (result == null) {
            result = createDefaultThreadContextMap();
        }
        return result;
    }
