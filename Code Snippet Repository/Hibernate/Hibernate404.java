    public static <T> T newProxy(Class<T> targetClass, Serializable id) {
        return ( T ) Proxy.newProxyInstance(
            targetClass.getClassLoader(),
            new Class[] {
                targetClass
            },
            new DataProxyHandler(
                targetClass.getName(),
                id
            )
        );
    }
