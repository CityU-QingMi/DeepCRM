    public static boolean isProxy(Object object) {
        Class<?> clazz = object.getClass();
        Boolean flag = isProxyCache.get(clazz);
        if (flag != null) {
            return flag;
        }

        boolean isProxy = isSpringAopProxy(object);

        isProxyCache.put(clazz, isProxy);
        return isProxy;
    }
