    public boolean isAvailable()
    {
        if (isStarted() && _unavailable==0)
            return true;
        try
        {
            getServlet();
        }
        catch(Exception e)
        {
            LOG.ignore(e);
        }

        return isStarted() && _unavailable==0;
    }
