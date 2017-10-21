    @Override
    public String alignToBaseDirectory( String path, File basedir )
    {
        String result = path;

        if ( path != null && basedir != null )
        {
            path = path.replace( '\\', File.separatorChar ).replace( '/', File.separatorChar );

            File file = new File( path );
            if ( file.isAbsolute() )
            {
                // path was already absolute, just normalize file separator and we're done
                result = file.getPath();
            }
            else if ( file.getPath().startsWith( File.separator ) )
            {
                // drive-relative Windows path, don't align with project directory but with drive root
                result = file.getAbsolutePath();
            }
            else
            {
                // an ordinary relative path, align with project directory
                result = new File( new File( basedir, path ).toURI().normalize() ).getAbsolutePath();
            }
        }

        return result;
    }
