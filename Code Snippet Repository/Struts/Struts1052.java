    @Override
    public Object buildBean(Class clazz, Map<String, Object> extraContext)
        throws Exception {
        Object bean = super.buildBean(clazz, extraContext);
        if(clazz.equals(ProxyInvocationAction.class)) {
            return Proxy.newProxyInstance(bean.getClass()
                .getClassLoader(), bean.getClass().getInterfaces(),
                new ProxyInvocationProxy(bean));

        }
        return bean;
    }
