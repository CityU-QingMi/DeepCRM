    public URI getURI()
    {
        try
        {
            return getURL().toURI();
        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }
    }
