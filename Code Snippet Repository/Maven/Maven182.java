    private String chopLeadingFileSeparator( String path )
    {
        if ( path != null )
        {
            if ( path.startsWith( "/" ) || path.startsWith( "\\" ) )
            {
                path = path.substring( 1 );
            }
        }
        return path;
    }
