    public void checkRequiredMavenVersion( PluginDescriptor pluginDescriptor )
        throws PluginIncompatibleException
    {
        String requiredMavenVersion = pluginDescriptor.getRequiredMavenVersion();
        if ( StringUtils.isNotBlank( requiredMavenVersion ) )
        {
            try
            {
                if ( !runtimeInformation.isMavenVersion( requiredMavenVersion ) )
                {
                    throw new PluginIncompatibleException( pluginDescriptor.getPlugin(),
                                                           "The plugin " + pluginDescriptor.getId()
                                                               + " requires Maven version " + requiredMavenVersion );
                }
            }
            catch ( RuntimeException e )
            {
                logger.warn( "Could not verify plugin's Maven prerequisite: " + e.getMessage() );
            }
        }
    }
