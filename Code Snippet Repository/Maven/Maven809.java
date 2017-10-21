    public PluginManagerException( MojoDescriptor mojoDescriptor, String message, MavenProject project,
                                   PlexusContainerException cause )
    {
        super( message, cause );

        this.project = project;

        PluginDescriptor pd = mojoDescriptor.getPluginDescriptor();
        pluginGroupId = pd.getGroupId();
        pluginArtifactId = pd.getArtifactId();
        pluginVersion = pd.getVersion();

        goal = mojoDescriptor.getGoal();
    }
