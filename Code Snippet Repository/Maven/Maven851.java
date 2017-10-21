    public DefaultPluginVersionRequest( Plugin plugin, RepositorySystemSession session,
                                        List<RemoteRepository> repositories )
    {
        setGroupId( plugin.getGroupId() );
        setArtifactId( plugin.getArtifactId() );

        setRepositorySession( session );

        setRepositories( repositories );
    }
