    public void setKeyStorePath(String keyStorePath)
    {
        try
        {
            _keyStoreResource = Resource.newResource(keyStorePath);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException(e);
        }
    }
