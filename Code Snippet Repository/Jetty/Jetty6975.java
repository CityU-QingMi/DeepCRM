    @Override
    public void destroy()
    {
        try
        {
            alreadySetToAttribute = false;
            if(localConfiguration)
            {
                configuration.stop();
            }
        }
        catch (Exception e)
        {
            LOG.ignore(e);
        }
    }
