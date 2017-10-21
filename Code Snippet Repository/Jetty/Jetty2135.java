    @Override
    protected void doStop() throws Exception
    {
        _webappTracker.close();
        
        //unregister ourselves
        if (_serviceRegForBundles != null)
        {
            try
            {
                _serviceRegForBundles.unregister();
            }
            catch (Exception e)
            {
                LOG.warn(e);
            }
        }
     
        super.doStop();
    }
