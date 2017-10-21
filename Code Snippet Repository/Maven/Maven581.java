    public DefaultBeanConfigurationRequest setConfiguration( Model model, String pluginGroupId,
                                                             String pluginArtifactId, String pluginExecutionId )
    {
        Plugin plugin = findPlugin( model, pluginGroupId, pluginArtifactId );
        if ( plugin != null )
        {
            if ( StringUtils.isNotEmpty( pluginExecutionId ) )
            {
                for ( PluginExecution execution : plugin.getExecutions() )
                {
                    if ( pluginExecutionId.equals( execution.getId() ) )
                    {
                        setConfiguration( execution.getConfiguration() );
                        break;
                    }
                }
            }
            else
            {
                setConfiguration( plugin.getConfiguration() );
            }
        }
        return this;
    }
