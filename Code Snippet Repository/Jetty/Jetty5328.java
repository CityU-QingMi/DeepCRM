    private synchronized void hook()
    {
        try
        {
            if (!_hooked)
                Runtime.getRuntime().addShutdownHook(this);
            _hooked=true;
        }
        catch(Exception e)
        {
            LOG.ignore(e);
            LOG.info("shutdown already commenced");
        }
    }
