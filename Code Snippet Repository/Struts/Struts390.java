    private static Object getSingletonTarget(Object candidate) {
        try {
            if (implementsInterface(candidate.getClass(), SPRING_ADVISED_CLASS_NAME)) {
                Object targetSource = MethodUtils.invokeMethod(candidate, "getTargetSource");
                if (implementsInterface(targetSource.getClass(), SPRING_SINGLETONTARGETSOURCE_CLASS_NAME)) {
                    return MethodUtils.invokeMethod(targetSource, "getTarget");
                }
            }
        } catch (Throwable ignored) {
        }

        return null;
    }
