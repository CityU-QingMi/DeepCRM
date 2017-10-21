    private List<File> parseExtClasspath( CliRequest cliRequest )
    {
        String extClassPath = cliRequest.userProperties.getProperty( EXT_CLASS_PATH );
        if ( extClassPath == null )
        {
            extClassPath = cliRequest.systemProperties.getProperty( EXT_CLASS_PATH );
        }

        List<File> jars = new ArrayList<>();

        if ( StringUtils.isNotEmpty( extClassPath ) )
        {
            for ( String jar : StringUtils.split( extClassPath, File.pathSeparator ) )
            {
                File file = resolveFile( new File( jar ), cliRequest.workingDirectory );

                slf4jLogger.debug( "  Included " + file );

                jars.add( file );
            }
        }

        return jars;
    }
