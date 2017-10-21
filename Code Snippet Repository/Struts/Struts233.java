    void addInjectors(Class clazz, List<Injector> injectors) {
        if (clazz == Object.class) {
            return;
        }

        // Add injectors for superclass first.
        addInjectors(clazz.getSuperclass(), injectors);

        // TODO (crazybob): Filter out overridden members.
        addInjectorsForFields(clazz.getDeclaredFields(), false, injectors);
        addInjectorsForMethods(clazz.getDeclaredMethods(), false, injectors);
    }
