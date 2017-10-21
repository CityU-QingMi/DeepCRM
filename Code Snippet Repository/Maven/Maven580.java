    public File translatePath( File path )
    {
        File result = path;

        if ( path != null && basedir != null )
        {
            if ( path.isAbsolute() )
            {
                // path is already absolute, we're done
            }
            else if ( path.getPath().startsWith( File.separator ) )
            {
                // drive-relative Windows path, don't align with base dir but with drive root
                result = path.getAbsoluteFile();
            }
            else
            {
                // an ordinary relative path, align with base dir
                result = new File( new File( basedir, path.getPath() ).toURI().normalize() ).getAbsoluteFile();
            }
        }

        return result;
    }
