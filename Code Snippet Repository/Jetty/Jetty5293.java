    public void setTrustStorePath(String trustStorePath)
    {
        try
        {
            _trustStoreResource = Resource.newResource(trustStorePath);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException(e);
        }
    }
