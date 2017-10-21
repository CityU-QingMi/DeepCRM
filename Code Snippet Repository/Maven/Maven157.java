    private static RepositorySystemSession rss( PlexusContainer c )
    {
        try
        {
            LegacySupport legacySupport = c.lookup( LegacySupport.class );

            return legacySupport.getRepositorySession();
        }
        catch ( ComponentLookupException e )
        {
            throw new IllegalStateException( e );
        }
    }
