    @Override
    public void configure( MavenProject project, MojoExecution mojoExecution, boolean allowPluginLevelConfig )
    {
        String g = mojoExecution.getGroupId();

        String a = mojoExecution.getArtifactId();

        Plugin plugin = findPlugin( g, a, project.getBuildPlugins() );

        if ( plugin == null && project.getPluginManagement() != null )
        {
            plugin = findPlugin( g, a, project.getPluginManagement().getPlugins() );
        }

        if ( plugin != null )
        {
            PluginExecution pluginExecution =
                findPluginExecution( mojoExecution.getExecutionId(), plugin.getExecutions() );

            Xpp3Dom pomConfiguration = null;

            if ( pluginExecution != null )
            {
                pomConfiguration = (Xpp3Dom) pluginExecution.getConfiguration();
            }
            else if ( allowPluginLevelConfig )
            {
                pomConfiguration = (Xpp3Dom) plugin.getConfiguration();
            }

            Xpp3Dom mojoConfiguration = ( pomConfiguration != null ) ? new Xpp3Dom( pomConfiguration ) : null;

            mojoConfiguration = Xpp3Dom.mergeXpp3Dom( mojoExecution.getConfiguration(), mojoConfiguration );

            mojoExecution.setConfiguration( mojoConfiguration );
        }
    }
