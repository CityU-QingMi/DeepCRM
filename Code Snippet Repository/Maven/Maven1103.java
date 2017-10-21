    public void testBuildExtensionsPluginLoading()
        throws Exception
    {
        RepositoryRequest repositoryRequest = new DefaultRepositoryRequest();
        repositoryRequest.setLocalRepository( getLocalRepository() );
        repositoryRequest.setRemoteRepositories( getPluginArtifactRepositories() );

        // prime realm cache
        MavenSession session = createMavenSession( getProject( "project-with-build-extensions-plugin" ) );
        MavenProject project = session.getCurrentProject();
        Plugin plugin = project.getPlugin( "org.apache.maven.its.plugins:maven-it-plugin" );

        PluginDescriptor pluginDescriptor =
            pluginManager.loadPlugin( plugin, session.getCurrentProject().getRemotePluginRepositories(),
                                      session.getRepositorySession() );
        ClassRealm pluginRealm = pluginManager.getPluginRealm( session, pluginDescriptor );
        
        assertEquals(pluginRealm, pluginDescriptor.getComponents().get(0).getRealm());
    }
