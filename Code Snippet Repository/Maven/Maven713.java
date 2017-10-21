    public void resolveMissingPluginVersions( MavenProject project, MavenSession session )
        throws PluginVersionResolutionException
    {
        Map<String, String> versions = new HashMap<>( 64 );

        for ( Plugin plugin : project.getBuildPlugins() )
        {
            if ( plugin.getVersion() == null )
            {
                PluginVersionRequest request = new DefaultPluginVersionRequest( plugin, session.getRepositorySession(),
                                                                                project.getRemotePluginRepositories() );
                plugin.setVersion( pluginVersionResolver.resolve( request ).getVersion() );
            }
            versions.put( plugin.getKey(), plugin.getVersion() );
        }

        PluginManagement pluginManagement = project.getPluginManagement();
        if ( pluginManagement != null )
        {
            for ( Plugin plugin : pluginManagement.getPlugins() )
            {
                if ( plugin.getVersion() == null )
                {
                    plugin.setVersion( versions.get( plugin.getKey() ) );
                    if ( plugin.getVersion() == null )
                    {
                        PluginVersionRequest request =
                            new DefaultPluginVersionRequest( plugin, session.getRepositorySession(),
                                                             project.getRemotePluginRepositories() );
                        plugin.setVersion( pluginVersionResolver.resolve( request ).getVersion() );
                    }
                }
            }
        }
    }
