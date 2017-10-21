    @Override
    public void lifeCycleStarted(LifeCycle lifecycle)
    {          
        try
        {
            _lifecycle.stop();
        }
        catch(Exception e)
        {
            LOG.warn(e);
        }  
    }
