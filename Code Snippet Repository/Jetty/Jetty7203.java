    @Override
    public void destroy()
    {
        try
        {
            factory.stop();
        }
        catch (Exception ignore)
        {
            // ignore;
        }
    }
