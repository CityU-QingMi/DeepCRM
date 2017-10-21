    public void testPluginDeclarationsRetainPomOrderAfterInjectionOfDefaultPlugins()
        throws Exception
    {
        PomTestWrapper pom = buildPom( "plugin-exec-order-with-lifecycle" );
        @SuppressWarnings( "unchecked" )
        List<Plugin> plugins = (List<Plugin>) pom.getValue( "build/plugins" );
        int resourcesPlugin = -1;
        int customPlugin = -1;
        for ( int i = 0; i < plugins.size(); i++ )
        {
            Plugin plugin = plugins.get( i );
            if ( "maven-resources-plugin".equals( plugin.getArtifactId() ) )
            {
                assertTrue( resourcesPlugin < 0 );
                resourcesPlugin = i;
            }
            else if ( "maven-it-plugin-log-file".equals( plugin.getArtifactId() ) )
            {
                assertTrue( customPlugin < 0 );
                customPlugin = i;
            }
        }
        assertTrue( plugins.toString(), customPlugin == resourcesPlugin - 1 );
    }
