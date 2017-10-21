    @Override
    public Object buildBean(Class clazz, Map<String, Object> extraContext) throws Exception {
        Object bean;

        try {
            // Decide to follow autowire strategy or use the legacy approach which mixes injection strategies
            if (alwaysRespectAutowireStrategy) {
                // Leave the creation up to Spring
                bean = autoWiringFactory.createBean(clazz, autowireStrategy, false);
                injectApplicationContext(bean);
                return injectInternalBeans(bean);
            } else if (enableAopSupport) {
                bean = autoWiringFactory.createBean(clazz, AutowireCapableBeanFactory.AUTOWIRE_CONSTRUCTOR, false);
                bean = autoWireBean(bean, autoWiringFactory);
                bean = autoWiringFactory.initializeBean(bean, bean.getClass().getName());
                return bean;
            } else {
                bean = autoWiringFactory.autowire(clazz, AutowireCapableBeanFactory.AUTOWIRE_CONSTRUCTOR, false);
                bean = autoWiringFactory.initializeBean(bean, bean.getClass().getName());
                return autoWireBean(bean, autoWiringFactory);
            }
        } catch (UnsatisfiedDependencyException e) {
            LOG.error("Error building bean", e);
            // Fall back
            return autoWireBean(super.buildBean(clazz, extraContext), autoWiringFactory);
        }
    }
