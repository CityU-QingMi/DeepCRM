    @ManagedOperation(value="")
    public int getCacheSeconds() throws ClassNotFoundException,
            IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {
        final Class policy = Class.forName(POLICY);
        final Object returnValue = policy.getMethod("get", (Class[]) null)
                .invoke(null, (Object[]) null);
        Integer seconds = (Integer) returnValue;

        return seconds.intValue();
    }
