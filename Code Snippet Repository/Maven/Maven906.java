    public Xpp3Dom getGoalConfiguration( String pluginGroupId, String pluginArtifactId, String executionId,
                                         String goalId )
    {
        Xpp3Dom dom = null;

        if ( getBuildPlugins() != null )
        {
            for ( Plugin plugin : getBuildPlugins() )
            {
                if ( pluginGroupId.equals( plugin.getGroupId() ) && pluginArtifactId.equals( plugin.getArtifactId() ) )
                {
                    dom = (Xpp3Dom) plugin.getConfiguration();

                    if ( executionId != null )
                    {
                        PluginExecution execution = plugin.getExecutionsAsMap().get( executionId );
                        if ( execution != null )
                        {
                            // NOTE: The PluginConfigurationExpander already merged the plugin-level config in
                            dom = (Xpp3Dom) execution.getConfiguration();
                        }
                    }
                    break;
                }
            }
        }

        if ( dom != null )
        {
            // make a copy so the original in the POM doesn't get messed with
            dom = new Xpp3Dom( dom );
        }

        return dom;
    }
