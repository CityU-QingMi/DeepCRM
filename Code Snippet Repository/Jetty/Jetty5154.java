    public String getName()
    {
        try
        {
            return (String)_getName.invoke(_logger);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
