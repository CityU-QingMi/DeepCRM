    public Object buildBean(String className, Map<String, Object> extraContext, boolean injectInternal) throws Exception {
        if (containsBean(className))
            return getBean(className);
        else {
            Class clazz = ClassLoaderUtil.loadClass(className, SpringOsgiObjectFactory.class);
            Object object = clazz.newInstance();
            if (injectInternal)
                injectInternalBeans(object);
            return object;
        }

    }
