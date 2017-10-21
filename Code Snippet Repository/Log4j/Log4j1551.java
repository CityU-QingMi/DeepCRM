    public static Object instantiateByClassName(final String className, final Class<?> superClass,
                                         final Object defaultValue) {
        if (className != null) {
            try {
                final Class<?> classObj = LoaderUtil.loadClass(className);
                if (!superClass.isAssignableFrom(classObj)) {
                    LOGGER.error("A \"{}\" object is not assignable to a \"{}\" variable.", className,
                        superClass.getName());
                    LOGGER.error("The class \"{}\" was loaded by [{}] whereas object of type [{}] was loaded by [{}].",
                        superClass.getName(), superClass.getClassLoader(), classObj.getName());
                    return defaultValue;
                }
                return classObj.newInstance();
            } catch (final Exception e) {
                LOGGER.error("Could not instantiate class [{}].", className, e);
            }
        }
        return defaultValue;
    }
