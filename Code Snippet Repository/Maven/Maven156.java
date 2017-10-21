    private static RepositorySystem rs( PlexusContainer c )
    {
        try
        {
            return c.lookup( RepositorySystem.class );
        }
        catch ( ComponentLookupException e )
        {
            throw new IllegalStateException( e );
        }
    }
