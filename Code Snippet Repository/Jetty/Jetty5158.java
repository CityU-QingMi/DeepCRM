    public void info(String msg, Throwable thrown)
    {
        try
        {
            _infoMT.invoke(_logger, msg, thrown);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
