    private static Builder<?> createBuilder(final Class<?> clazz)
        throws InvocationTargetException, IllegalAccessException {
        for (final Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(PluginBuilderFactory.class) &&
                Modifier.isStatic(method.getModifiers()) &&
                TypeUtil.isAssignable(Builder.class, method.getReturnType())) {
                ReflectionUtil.makeAccessible(method);
                return (Builder<?>) method.invoke(null);
            }
        }
        return null;
    }
