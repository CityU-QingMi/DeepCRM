    private void discoverPluginComponents( final ClassRealm pluginRealm, Plugin plugin,
                                           PluginDescriptor pluginDescriptor )
        throws PluginContainerException
    {
        try
        {
            if ( pluginDescriptor != null )
            {
                for ( ComponentDescriptor<?> componentDescriptor : pluginDescriptor.getComponents() )
                {
                    componentDescriptor.setRealm( pluginRealm );
                    container.addComponentDescriptor( componentDescriptor );
                }
            }

            ( (DefaultPlexusContainer) container ).discoverComponents( pluginRealm, new SessionScopeModule( container ),
                                                                       new MojoExecutionScopeModule( container ) );
        }
        catch ( ComponentLookupException | CycleDetectedInComponentGraphException e )
        {
            throw new PluginContainerException( plugin, pluginRealm,
                                                "Error in component graph of plugin " + plugin.getId() + ": "
                                                    + e.getMessage(), e );
        }
    }
