    protected String appendPath( String parentPath, String childPath, String pathAdjustment, boolean appendPaths )
    {
        String uncleanPath = parentPath;

        if ( appendPaths )
        {
            if ( pathAdjustment != null )
            {
                uncleanPath += "/" + pathAdjustment;
            }

            if ( childPath != null )
            {
                uncleanPath += "/" + childPath;
            }
        }

        String cleanedPath = "";

        int protocolIdx = uncleanPath.indexOf( "://" );

        if ( protocolIdx > -1 )
        {
            cleanedPath = uncleanPath.substring( 0, protocolIdx + 3 );
            uncleanPath = uncleanPath.substring( protocolIdx + 3 );
        }

        if ( uncleanPath.startsWith( "/" ) )
        {
            cleanedPath += "/";
        }

        return cleanedPath + resolvePath( uncleanPath );
    }
