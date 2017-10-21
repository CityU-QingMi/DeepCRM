    protected void alias(Class type, String key, ContainerBuilder builder, Properties props, Scope scope) {
        if (!builder.contains(type, Container.DEFAULT_NAME)) {
            String foundName = props.getProperty(key, DEFAULT_BEAN_NAME);
            if (builder.contains(type, foundName)) {
                LOG.trace("Choosing bean ({}) for ({})", foundName, type.getName());
                builder.alias(type, foundName, Container.DEFAULT_NAME);
            } else {
                try {
                    Class cls = ClassLoaderUtil.loadClass(foundName, this.getClass());
                    LOG.trace("Choosing bean ({}) for ({})", cls.getName(), type.getName());
                    builder.factory(type, cls, scope);
                } catch (ClassNotFoundException ex) {
                    // Perhaps a spring bean id, so we'll delegate to the object factory at runtime
                    LOG.trace("Choosing bean ({}) for ({}) to be loaded from the ObjectFactory", foundName, type.getName());
                    if (DEFAULT_BEAN_NAME.equals(foundName)) {
                        // Probably an optional bean, will ignore
                    } else {
                        if (ObjectFactory.class != type) {
                            builder.factory(type, new ObjectFactoryDelegateFactory(foundName, type), scope);
                        } else {
                            throw new ConfigurationException("Cannot locate the chosen ObjectFactory implementation: " + foundName);
                        }
                    }
                }
            }
        } else {
            LOG.warn("Unable to alias bean type ({}), default mapping already assigned.", type.getName());
        }
    }
