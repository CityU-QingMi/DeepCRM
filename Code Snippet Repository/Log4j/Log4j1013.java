    private static Method findFactoryMethod(final Class<?> clazz) {
        for (final Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(PluginFactory.class) &&
                Modifier.isStatic(method.getModifiers())) {
                ReflectionUtil.makeAccessible(method);
                return method;
            }
        }
        throw new IllegalStateException("No factory method found for class " + clazz.getName());
    }
