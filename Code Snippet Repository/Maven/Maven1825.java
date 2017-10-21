    public String getVersion()
    {
        if ( version != null )
        {
            return version;
        }
        else
        {
            return artifact.getVersion();
        }
    }
