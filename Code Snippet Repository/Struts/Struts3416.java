    public Object buildBean(String className, Map<String, Object> extraContext, boolean injectInternal) throws Exception {
        try {
            return delegateObjectFactory.buildBean(className, extraContext, injectInternal);
        } catch (Exception e) {
            Object object = bundleResourceLoader.loadClass(className).newInstance();
            if (injectInternal)
                injectInternalBeans(object);
            return object;
        }
    }
