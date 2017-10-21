    private Source getSettingsSource( File settingsFile, Source settingsSource )
    {
        if ( settingsSource != null )
        {
            return settingsSource;
        }
        else if ( settingsFile != null && settingsFile.exists() )
        {
            return new FileSource( settingsFile );
        }
        return null;
    }
