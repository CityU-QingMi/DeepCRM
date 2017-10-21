    @Deprecated
    public MavenSession( PlexusContainer container, RepositorySystemSession repositorySession,
                         MavenExecutionRequest request, MavenExecutionResult result )
    {
        this.container = container;
        this.request = request;
        this.result = result;
        this.settings = new SettingsAdapter( request );
        this.repositorySession = repositorySession;
    }
