    @Override
    public Resource getResource(String path)
    {
        try
        {
            return addPath(path);
        }
        catch(Exception e)
        {
            LOG.debug(e);
            return null;
        }
    }
