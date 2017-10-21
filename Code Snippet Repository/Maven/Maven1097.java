    public void testPluginLoading()
        throws Exception
    {
        MavenSession session = createMavenSession( null );
        Plugin plugin = new Plugin();
        plugin.setGroupId( "org.apache.maven.its.plugins" );
        plugin.setArtifactId( "maven-it-plugin" );
        plugin.setVersion( "0.1" );
        PluginDescriptor pluginDescriptor =
            pluginManager.loadPlugin( plugin, session.getCurrentProject().getRemotePluginRepositories(),
                                      session.getRepositorySession() );
        assertNotNull( pluginDescriptor );
    }
