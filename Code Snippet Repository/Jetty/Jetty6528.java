    @Override
    protected void doStop() throws Exception
    {
        if(LOG.isDebugEnabled())
            LOG.debug("stopping - {}",this);
        try
        {
            close(StatusCode.SHUTDOWN,"Shutdown");
        }
        catch (Throwable t)
        {
            LOG.debug("During Connection Shutdown",t);
        }
        super.doStop();
    }
