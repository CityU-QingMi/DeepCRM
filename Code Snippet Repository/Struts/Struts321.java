    protected boolean isClassExcluded(Class<?> clazz) {
        if (clazz == Object.class || (clazz == Class.class && !allowStaticMethodAccess)) {
            return true;
        }
        for (Class<?> excludedClass : excludedClasses) {
            if (clazz.isAssignableFrom(excludedClass)) {
                return true;
            }
        }
        return false;
    }
