    @Override
    public URL getURL()
    {
        try
        {
            return path.toUri().toURL();
        }
        catch (MalformedURLException e)
        {
            return null;
        }
    }
