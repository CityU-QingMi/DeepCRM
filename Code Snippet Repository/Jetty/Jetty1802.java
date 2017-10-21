    public boolean abort() throws LoginException
    {
        try
        {
            _rootContext.close();
        }
        catch (NamingException e)
        {
            throw new LoginException("error closing root context: " + e.getMessage());
        }

        return super.abort();
    }
