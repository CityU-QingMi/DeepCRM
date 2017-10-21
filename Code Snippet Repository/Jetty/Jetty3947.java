    @Override
    public void initialize ()
    throws Exception
    {
        if(!_initialized)
        {
            super.initialize();
            if (_extInstance || _initOnStartup)
            {
                try
                {
                    initServlet();
                }
                catch(Exception e)
                {
                    if (_servletHandler.isStartWithUnavailable())
                        LOG.ignore(e);
                    else
                        throw e;
                }
            }
        }
        _initialized = true;
    }
