    public void warn(String msg, Object... args)
    {
        try
        {
            _warnMAA.invoke(_logger, args);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
