    public void initialize()
        throws InitializationException
    {
        String mavenVersion = rtInfo.getMavenVersion();

        if ( StringUtils.isEmpty( mavenVersion ) )
        {
            throw new InitializationException( "Unable to read Maven version from maven-core" );
        }

        applicationVersion = new DefaultArtifactVersion( mavenVersion );
    }
