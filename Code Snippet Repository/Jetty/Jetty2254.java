    protected void injectMethod (Method method, Object injectable)
    {
        try
        {
            boolean accessibility = method.isAccessible();
            method.setAccessible(true);
            method.invoke(injectable, new Object[] {lookupInjectedValue()});
            method.setAccessible(accessibility);
        }
        catch (Exception e)
        {
            LOG.warn(e);
            throw new IllegalStateException("Inject failed for method "+method.getName());
        }
    }
