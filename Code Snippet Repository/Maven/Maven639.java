    @Deprecated
    public MavenSession( PlexusContainer container, Settings settings, ArtifactRepository localRepository,
                         EventDispatcher eventDispatcher, ReactorManager unused, List<String> goals,
                         String executionRootDir, Properties executionProperties, Properties userProperties,
                         Date startTime )
    {
        this.container = container;
        this.settings = settings;
        this.executionProperties = executionProperties;
        this.request = new DefaultMavenExecutionRequest();
        this.request.setUserProperties( userProperties );
        this.request.setLocalRepository( localRepository );
        this.request.setGoals( goals );
        this.request.setBaseDirectory( ( executionRootDir != null ) ? new File( executionRootDir ) : null );
        this.request.setStartTime( startTime );
    }
