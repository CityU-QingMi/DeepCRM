    public String alignToBaseDirectory( String path, File basedir )
    {
        if ( basedir == null )
        {
            return path;
        }

        if ( path == null )
        {
            return null;
        }

        String s = stripBasedirToken( path );

        File file = new File( s );
        if ( file.isAbsolute() )
        {
            // path was already absolute, just normalize file separator and we're done
            s = file.getPath();
        }
        else if ( file.getPath().startsWith( File.separator ) )
        {
            // drive-relative Windows path, don't align with project directory but with drive root
            s = file.getAbsolutePath();
        }
        else
        {
            // an ordinary relative path, align with project directory
            s = new File( new File( basedir, s ).toURI().normalize() ).getAbsolutePath();
        }

        return s;
    }
