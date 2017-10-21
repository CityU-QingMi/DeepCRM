    void injectStatics(List<Class<?>> staticInjections) {
        final List<Injector> injectors = new ArrayList<>();

        for (Class<?> clazz : staticInjections) {
            addInjectorsForFields(clazz.getDeclaredFields(), true, injectors);
            addInjectorsForMethods(clazz.getDeclaredMethods(), true, injectors);
        }

        callInContext(new ContextualCallable<Void>() {
            public Void call(InternalContext context) {
                for (Injector injector : injectors) {
                    injector.inject(context, null);
                }
                return null;
            }
        });
    }
