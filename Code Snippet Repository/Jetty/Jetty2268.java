    public void callback(Object instance)
    {
        try
        {
            super.callback(instance);
        }
        catch (Exception e)
        {
            LOG.warn("Ignoring exception thrown on preDestroy call to "+getTargetClass()+"."+getTarget().getName(), e);
        }
    }
