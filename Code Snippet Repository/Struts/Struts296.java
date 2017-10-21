    public BeanInfo getBeanInfo(Class clazz) throws IntrospectionException {
        synchronized (beanInfoCache) {
            BeanInfo beanInfo;
            beanInfo = beanInfoCache.get(clazz);
            if (beanInfo == null) {
                beanInfo = Introspector.getBeanInfo(clazz, Object.class);
                beanInfoCache.putIfAbsent(clazz, beanInfo);
            }
            return beanInfo;
        }
    }
