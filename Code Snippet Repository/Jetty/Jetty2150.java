    @Override
    protected void doStop() throws Exception
    {
        if (_tracker != null)
            _tracker.close();
        
        //unregister ourselves 
        if (_serviceRegForServices != null)
        {
            try
            {
                _serviceRegForServices.unregister();
            }
            catch (Exception e)
            {
                LOG.warn(e);
            }
        }
        super.doStop();
    }
