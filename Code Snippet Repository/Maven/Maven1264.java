    private PomTestWrapper buildPom( String pomPath )
        throws Exception
    {
        File pomFile = new File( testDirectory + File.separator + pomPath, "pom.xml" );
        File settingsFile = new File( testDirectory + File.separator + pomPath, "settings.xml" );
        Settings settings = readSettingsFile( settingsFile );

        ProjectBuildingRequest config = new DefaultProjectBuildingRequest();

        for ( org.apache.maven.settings.Profile rawProfile : settings.getProfiles() )
        {
            Profile profile = SettingsUtils.convertFromSettingsProfile( rawProfile );
            config.addProfile( profile );
        }

        String localRepoUrl =
            System.getProperty( "maven.repo.local", System.getProperty( "user.home" ) + "/.m2/repository" );
        localRepoUrl = "file://" + localRepoUrl;
        config.setLocalRepository(
            repositorySystem.createArtifactRepository( "local", localRepoUrl, new DefaultRepositoryLayout(), null,
                                                       null ) );
        config.setActiveProfileIds( settings.getActiveProfiles() );

        DefaultRepositorySystemSession repoSession = MavenRepositorySystemUtils.newSession();
        LocalRepository localRepo = new LocalRepository( config.getLocalRepository().getBasedir() );
        repoSession.setLocalRepositoryManager(
            new SimpleLocalRepositoryManagerFactory().newInstance( repoSession, localRepo ) );
        config.setRepositorySession( repoSession );

        return new PomTestWrapper( pomFile, projectBuilder.build( pomFile, config ).getProject() );
    }
