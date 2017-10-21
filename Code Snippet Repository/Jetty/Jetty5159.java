    public void setDebugEnabled(boolean enabled)
    {
        try
        {
            _setDebugEnabledE.invoke(_logger, enabled);
            _debug = enabled;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
