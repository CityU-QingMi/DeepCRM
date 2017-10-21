    private void createPluginRealm( PluginDescriptor pluginDescriptor, MavenSession session, ClassLoader parent,
                                    Map<String, ClassLoader> foreignImports, DependencyFilter filter )
        throws PluginResolutionException, PluginContainerException
    {
        Plugin plugin = Validate.notNull( pluginDescriptor.getPlugin(), "pluginDescriptor.plugin cannot be null" );

        Artifact pluginArtifact =
            Validate.notNull( pluginDescriptor.getPluginArtifact(), "pluginDescriptor.pluginArtifact cannot be null" );

        MavenProject project = session.getCurrentProject();

        final ClassRealm pluginRealm;
        final List<Artifact> pluginArtifacts;

        RepositorySystemSession repositorySession = session.getRepositorySession();
        DependencyFilter dependencyFilter = project.getExtensionDependencyFilter();
        dependencyFilter = AndDependencyFilter.newInstance( dependencyFilter, filter );

        DependencyNode root =
            pluginDependenciesResolver.resolve( plugin, RepositoryUtils.toArtifact( pluginArtifact ), dependencyFilter,
                                                project.getRemotePluginRepositories(), repositorySession );

        PreorderNodeListGenerator nlg = new PreorderNodeListGenerator();
        root.accept( nlg );

        pluginArtifacts = toMavenArtifacts( root, nlg );

        pluginRealm = classRealmManager.createPluginRealm( plugin, parent, null, foreignImports,
                                                           toAetherArtifacts( pluginArtifacts ) );

        discoverPluginComponents( pluginRealm, plugin, pluginDescriptor );

        pluginDescriptor.setClassRealm( pluginRealm );
        pluginDescriptor.setArtifacts( pluginArtifacts );
    }
