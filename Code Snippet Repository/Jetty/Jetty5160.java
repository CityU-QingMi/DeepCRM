    public void debug(String msg, Object... args)
    {
        if (!_debug)
            return;

        try
        {
            _debugMAA.invoke(_logger, args);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
