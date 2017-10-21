    private Plugin findSitePlugin( Build build )
    {
        for ( Plugin plugin : build.getPlugins() )
        {
            if ( isSitePlugin( plugin ) )
            {
                return plugin;
            }
        }

        PluginManagement pluginManagement = build.getPluginManagement();
        if ( pluginManagement != null )
        {
            for ( Plugin plugin : pluginManagement.getPlugins() )
            {
                if ( isSitePlugin( plugin ) )
                {
                    return plugin;
                }
            }
        }

        return null;
    }
