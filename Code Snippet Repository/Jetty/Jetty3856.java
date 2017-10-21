    @Override
    public void doStop()
        throws Exception
    {
        if (_filter!=null)
        {
            try
            {
                destroyInstance(_filter);
            }
            catch (Exception e)
            {
                LOG.warn(e);
            }
        }
        if (!_extInstance)
            _filter=null;

        _config=null;
        _initialized = false;
        super.doStop();
    }
