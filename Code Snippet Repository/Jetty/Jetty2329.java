    @Override
    public void destroy()
    {
        try
        {
            _client.stop();
        }
        catch (Exception x)
        {
            if (_log.isDebugEnabled())
                _log.debug(x);
        }
    }
