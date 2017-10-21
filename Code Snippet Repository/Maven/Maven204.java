    Date readLastUpdated( File touchfile, String key )
    {
        getLogger().debug( "Searching for " + key + " in resolution tracking file." );

        Properties props = read( touchfile );
        if ( props != null )
        {
            String rawVal = props.getProperty( key );
            if ( rawVal != null )
            {
                try
                {
                    return new Date( Long.parseLong( rawVal ) );
                }
                catch ( NumberFormatException e )
                {
                    getLogger().debug( "Cannot parse lastUpdated date: \'" + rawVal + "\'. Ignoring.", e );
                }
            }
        }
        return null;
    }
