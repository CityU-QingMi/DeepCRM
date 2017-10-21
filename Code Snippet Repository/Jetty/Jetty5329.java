    private synchronized void unhook()
    {
        try
        {
            _hooked=false;
            Runtime.getRuntime().removeShutdownHook(this);
        }
        catch(Exception e)
        {
            LOG.ignore(e);
            LOG.debug("shutdown already commenced");
        }
    }
