    public void testMojoDescriptorRetrieval()
        throws Exception
    {
        MavenSession session = createMavenSession( null );
        String goal = "it";
        Plugin plugin = new Plugin();
        plugin.setGroupId( "org.apache.maven.its.plugins" );
        plugin.setArtifactId( "maven-it-plugin" );
        plugin.setVersion( "0.1" );

        MojoDescriptor mojoDescriptor =
            pluginManager.getMojoDescriptor( plugin, goal, session.getCurrentProject().getRemotePluginRepositories(),
                                             session.getRepositorySession() );
        assertNotNull( mojoDescriptor );
        assertEquals( goal, mojoDescriptor.getGoal() );
        // igorf: plugin realm comes later
        // assertNotNull( mojoDescriptor.getRealm() );

        PluginDescriptor pluginDescriptor = mojoDescriptor.getPluginDescriptor();
        assertNotNull( pluginDescriptor );
        assertEquals( "org.apache.maven.its.plugins", pluginDescriptor.getGroupId() );
        assertEquals( "maven-it-plugin", pluginDescriptor.getArtifactId() );
        assertEquals( "0.1", pluginDescriptor.getVersion() );
    }
