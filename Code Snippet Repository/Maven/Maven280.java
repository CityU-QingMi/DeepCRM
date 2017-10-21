    private static void checkVertex( MetadataGraphVertex v )
        throws MetadataResolutionException
    {
        if ( v == null )
        {
            throw new MetadataResolutionException( "null vertex" );
        }
        if ( v.getMd() == null )
        {
            throw new MetadataResolutionException( "vertex without metadata" );
        }
    }
