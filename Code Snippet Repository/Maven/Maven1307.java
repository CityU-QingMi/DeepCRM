    void initialize( CliRequest cliRequest )
        throws ExitException
    {
        if ( cliRequest.workingDirectory == null )
        {
            cliRequest.workingDirectory = System.getProperty( "user.dir" );
        }

        if ( cliRequest.multiModuleProjectDirectory == null )
        {
            String basedirProperty = System.getProperty( MULTIMODULE_PROJECT_DIRECTORY );
            if ( basedirProperty == null )
            {
                System.err.format(
                    "-D%s system property is not set.", MULTIMODULE_PROJECT_DIRECTORY );
                throw new ExitException( 1 );
            }
            File basedir = basedirProperty != null ? new File( basedirProperty ) : new File( "" );
            try
            {
                cliRequest.multiModuleProjectDirectory = basedir.getCanonicalFile();
            }
            catch ( IOException e )
            {
                cliRequest.multiModuleProjectDirectory = basedir.getAbsoluteFile();
            }
        }

        //
        // Make sure the Maven home directory is an absolute path to save us from confusion with say drive-relative
        // Windows paths.
        //
        String mavenHome = System.getProperty( "maven.home" );

        if ( mavenHome != null )
        {
            System.setProperty( "maven.home", new File( mavenHome ).getAbsolutePath() );
        }
    }
