    protected PluginManagerException( MojoDescriptor mojoDescriptor, MavenProject project, String message,
                                      Throwable cause )
    {
        super( message, cause );
        this.project = project;
        pluginGroupId = mojoDescriptor.getPluginDescriptor().getGroupId();
        pluginArtifactId = mojoDescriptor.getPluginDescriptor().getArtifactId();
        pluginVersion = mojoDescriptor.getPluginDescriptor().getVersion();
        goal = mojoDescriptor.getGoal();
    }
