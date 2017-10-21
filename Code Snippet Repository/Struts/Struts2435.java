	@Override
    @SuppressWarnings({ "", "" })
    public Object buildBean(String className, Map<String, Object> extraContext, boolean injectInternal)
            throws Exception {

        Class<?> clazz = getClassInstance(className);
        InjectionTarget injectionTarget = getInjectionTarget(clazz);

        // a separate CreationalContext is required for every bean
        final CreationalContext ctx = buildNonContextualCreationalContext(beanManager);

        Object o = injectionTarget.produce(ctx);
        injectionTarget.inject(o, ctx);
        injectionTarget.postConstruct(o);

        if (injectInternal) {
            injectInternalBeans(o);
        }

        return o;
    }
