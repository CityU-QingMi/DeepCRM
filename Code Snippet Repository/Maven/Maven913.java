    private void addPath( List<String> paths, String path )
    {
        if ( path != null )
        {
            path = path.trim();
            if ( path.length() > 0 )
            {
                File file = new File( path );
                if ( file.isAbsolute() )
                {
                    path = file.getAbsolutePath();
                }
                else
                {
                    path = new File( getBasedir(), path ).getAbsolutePath();
                }

                if ( !paths.contains( path ) )
                {
                    paths.add( path );
                }
            }
        }
    }
