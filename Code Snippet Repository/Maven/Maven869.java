    public DefaultModelBuildingListener( MavenProject project, ProjectBuildingHelper projectBuildingHelper,
                                         ProjectBuildingRequest projectBuildingRequest )
    {
        this.project = Validate.notNull( project, "project cannot be null" );
        this.projectBuildingHelper = Validate.notNull( projectBuildingHelper, "projectBuildingHelper cannot be null" );
        this.projectBuildingRequest =
            Validate.notNull( projectBuildingRequest, "projectBuildingRequest cannot be null" );
        this.remoteRepositories = projectBuildingRequest.getRemoteRepositories();
        this.pluginRepositories = projectBuildingRequest.getPluginArtifactRepositories();
    }
