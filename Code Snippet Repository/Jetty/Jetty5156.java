    public void warn(String msg, Throwable thrown)
    {
        try
        {
            _warnMT.invoke(_logger, msg, thrown);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
