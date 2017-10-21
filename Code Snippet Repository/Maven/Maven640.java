    @Deprecated
    public MavenSession( PlexusContainer container, MavenExecutionRequest request, MavenExecutionResult result,
                         List<MavenProject> projects )
    {
        this.container = container;
        this.request = request;
        this.result = result;
        this.settings = new SettingsAdapter( request );
        setProjects( projects );
    }
