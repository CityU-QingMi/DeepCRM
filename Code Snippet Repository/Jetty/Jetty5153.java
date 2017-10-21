    protected Logger newLogger(String fullname)
    {
        try
        {
            Object logger=_getLoggerN.invoke(_logger, fullname);
            return new LoggerLog(logger);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return this;
        }
    }
