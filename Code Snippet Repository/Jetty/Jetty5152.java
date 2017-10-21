    public void debug(String msg, long value)
    {
        if (!_debug)
            return;

        try
        {
            _debugMAA.invoke(_logger, new Object[]{new Long(value)});
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
