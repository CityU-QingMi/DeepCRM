    public void testPluginRealmCache()
        throws Exception
    {
        RepositoryRequest repositoryRequest = new DefaultRepositoryRequest();
        repositoryRequest.setLocalRepository( getLocalRepository() );
        repositoryRequest.setRemoteRepositories( getPluginArtifactRepositories() );

        // prime realm cache
        MavenSession session = createMavenSession( getProject( "project-contributing-system-scope-plugin-dep" ) );
        MavenProject project = session.getCurrentProject();
        Plugin plugin = project.getPlugin( "org.apache.maven.its.plugins:maven-it-plugin" );

        PluginDescriptor pluginDescriptor =
            pluginManager.loadPlugin( plugin, session.getCurrentProject().getRemotePluginRepositories(),
                                      session.getRepositorySession() );
        pluginManager.getPluginRealm( session, pluginDescriptor );

        assertEquals( 1, pluginDescriptor.getDependencies().size() );

        for ( ComponentDescriptor<?> descriptor : pluginDescriptor.getComponents() )
        {
            assertNotNull( descriptor.getRealm() );
            assertNotNull( descriptor.getImplementationClass() );
        }

        // reload plugin realm from cache
        session = createMavenSession( getProject( "project-contributing-system-scope-plugin-dep" ) );
        project = session.getCurrentProject();
        plugin = project.getPlugin( "org.apache.maven.its.plugins:maven-it-plugin" );

        pluginDescriptor =
            pluginManager.loadPlugin( plugin, session.getCurrentProject().getRemotePluginRepositories(),
                                      session.getRepositorySession() );
        pluginManager.getPluginRealm( session, pluginDescriptor );

        assertEquals( 1, pluginDescriptor.getDependencies().size() );

        for ( ComponentDescriptor<?> descriptor : pluginDescriptor.getComponents() )
        {
            assertNotNull( descriptor.getRealm() );
            assertNotNull( descriptor.getImplementationClass() );
        }
    }
