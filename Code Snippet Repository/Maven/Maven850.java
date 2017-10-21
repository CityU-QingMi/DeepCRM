    public DefaultPluginVersionRequest( Plugin plugin, MavenSession session )
    {
        setGroupId( plugin.getGroupId() );
        setArtifactId( plugin.getArtifactId() );

        setRepositorySession( session.getRepositorySession() );

        MavenProject project = session.getCurrentProject();
        if ( project != null )
        {
            setRepositories( project.getRemotePluginRepositories() );
        }
    }
