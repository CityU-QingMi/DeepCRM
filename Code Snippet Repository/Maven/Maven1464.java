    private void expand( List<Plugin> plugins )
    {
        for ( Plugin plugin : plugins )
        {
            Xpp3Dom pluginConfiguration = (Xpp3Dom) plugin.getConfiguration();

            if ( pluginConfiguration != null )
            {
                for ( PluginExecution execution : plugin.getExecutions() )
                {
                    Xpp3Dom executionConfiguration = (Xpp3Dom) execution.getConfiguration();

                    executionConfiguration =
                        Xpp3Dom.mergeXpp3Dom( executionConfiguration, new Xpp3Dom( pluginConfiguration ) );

                    execution.setConfiguration( executionConfiguration );
                }
            }
        }
    }
