    public void info(String msg, Object... args)
    {
        try
        {
            _infoMAA.invoke(_logger, args);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
