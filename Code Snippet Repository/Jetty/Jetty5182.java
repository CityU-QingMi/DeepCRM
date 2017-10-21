    @Override
    public URL getURL()
    {
        try
        {
            return _uri.toURL();
        }
        catch (MalformedURLException e)
        {
            throw new IllegalStateException(e);
        }
    }
