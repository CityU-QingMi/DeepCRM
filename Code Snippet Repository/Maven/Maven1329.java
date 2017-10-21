    private List<Artifact> resolveExtension( CoreExtension extension, RepositorySystemSession repoSession,
                                             List<RemoteRepository> repositories, DependencyFilter dependencyFilter )
        throws PluginResolutionException
    {
        Plugin plugin = new Plugin();
        plugin.setGroupId( extension.getGroupId() );
        plugin.setArtifactId( extension.getArtifactId() );
        plugin.setVersion( extension.getVersion() );

        DependencyNode root =
            pluginDependenciesResolver.resolveCoreExtension( plugin, dependencyFilter, repositories, repoSession );
        PreorderNodeListGenerator nlg = new PreorderNodeListGenerator();
        root.accept( nlg );
        List<Artifact> artifacts = nlg.getArtifacts( false );

        return artifacts;
    }
