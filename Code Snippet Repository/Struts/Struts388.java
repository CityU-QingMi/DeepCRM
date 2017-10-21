    private static Class<?> springUltimateTargetClass(Object candidate) {
        Object current = candidate;
        Class<?> result = null;
        while (null != current && implementsInterface(current.getClass(), SPRING_TARGETCLASSAWARE_CLASS_NAME)) {
            try {
                result = (Class<?>) MethodUtils.invokeMethod(current, "getTargetClass");
            } catch (Throwable ignored) {
            }
            current = getSingletonTarget(current);
        }
        if (result == null) {
            Class<?> clazz = candidate.getClass();
            result = (isCglibProxyClass(clazz) ? clazz.getSuperclass() : candidate.getClass());
        }
        return result;
    }
