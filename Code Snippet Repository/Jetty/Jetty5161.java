    public void debug(String msg, Throwable th)
    {
        if (!_debug)
            return;

        try
        {
            _debugMT.invoke(_logger, msg, th);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
