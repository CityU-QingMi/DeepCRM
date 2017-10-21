    public static Class<?> ultimateTargetClass(Object candidate) {
        Class<?> result = null;
        if (isSpringAopProxy(candidate))
            result = springUltimateTargetClass(candidate);

        if (result == null) {
            result = candidate.getClass();
        }

        return result;
    }
