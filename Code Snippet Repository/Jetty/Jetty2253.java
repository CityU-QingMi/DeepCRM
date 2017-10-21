    protected void injectField (Field field, Object injectable)
    {
        try
        {
            boolean accessibility = field.isAccessible();
            field.setAccessible(true);
            field.set(injectable, lookupInjectedValue());
            field.setAccessible(accessibility);
        }
        catch (Exception e)
        {
            LOG.warn(e);
            throw new IllegalStateException("Inject failed for field "+field.getName());
        }
    }
