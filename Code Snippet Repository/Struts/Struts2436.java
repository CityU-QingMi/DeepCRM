    protected InjectionTarget<?> getInjectionTarget(Class<?> clazz) {
        InjectionTarget<?> result;
        result = injectionTargetCache.get(clazz);
        if (result == null) {
            result = beanManager.createInjectionTarget(beanManager.createAnnotatedType(clazz));
            injectionTargetCache.put(clazz, result);
        }

        return result;
    }
