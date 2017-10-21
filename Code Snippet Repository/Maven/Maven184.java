    public String unalignFromBaseDirectory( String path, File basedir )
    {
        if ( basedir == null )
        {
            return path;
        }

        if ( path == null )
        {
            return null;
        }

        path = path.trim();

        String base = basedir.getAbsolutePath();
        if ( path.startsWith( base ) )
        {
            path = chopLeadingFileSeparator( path.substring( base.length() ) );
        }

        if ( path.length() <= 0 )
        {
            path = ".";
        }

        if ( !new File( path ).isAbsolute() )
        {
            path = path.replace( '\\', '/' );
        }

        return path;
    }
