    private PomTestWrapper buildPom( String pomPath, boolean lenientValidation, Properties executionProperties,
                                     String... profileIds )
        throws Exception
    {
        File pomFile = new File( testDirectory, pomPath );
        if ( pomFile.isDirectory() )
        {
            pomFile = new File( pomFile, "pom.xml" );
        }

        ProjectBuildingRequest config = new DefaultProjectBuildingRequest();

        String localRepoUrl =
            System.getProperty( "maven.repo.local", System.getProperty( "user.home" ) + "/.m2/repository" );
        localRepoUrl = "file://" + localRepoUrl;
        config.setLocalRepository( repositorySystem.createArtifactRepository( "local", localRepoUrl, new DefaultRepositoryLayout(), null, null ) );
        config.setActiveProfileIds( Arrays.asList( profileIds ) );
        config.setSystemProperties( executionProperties );
        config.setUserProperties( executionProperties );
        config.setValidationLevel( lenientValidation ? ModelBuildingRequest.VALIDATION_LEVEL_MAVEN_2_0
                        : ModelBuildingRequest.VALIDATION_LEVEL_STRICT );

        DefaultRepositorySystemSession repoSession = MavenRepositorySystemUtils.newSession();
        LocalRepository localRepo = new LocalRepository( config.getLocalRepository().getBasedir() );
        repoSession.setLocalRepositoryManager( new SimpleLocalRepositoryManagerFactory().newInstance( repoSession, localRepo ) );
        config.setRepositorySession( repoSession );

        return new PomTestWrapper( pomFile, projectBuilder.build( pomFile, config ).getProject() );
    }
