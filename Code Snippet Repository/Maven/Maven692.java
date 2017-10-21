    private List<MojoExecution> calculateForkedGoal( MojoExecution mojoExecution, MavenSession session,
                                                     MavenProject project,
                                                     Collection<MojoDescriptor> alreadyForkedExecutions )
        throws MojoNotFoundException, PluginNotFoundException, PluginResolutionException,
        PluginDescriptorParsingException, NoPluginFoundForPrefixException, InvalidPluginDescriptorException,
        LifecyclePhaseNotFoundException, LifecycleNotFoundException, PluginVersionResolutionException
    {
        MojoDescriptor mojoDescriptor = mojoExecution.getMojoDescriptor();

        PluginDescriptor pluginDescriptor = mojoDescriptor.getPluginDescriptor();

        String forkedGoal = mojoDescriptor.getExecuteGoal();

        MojoDescriptor forkedMojoDescriptor = pluginDescriptor.getMojo( forkedGoal );
        if ( forkedMojoDescriptor == null )
        {
            throw new MojoNotFoundException( forkedGoal, pluginDescriptor );
        }

        if ( alreadyForkedExecutions.contains( forkedMojoDescriptor ) )
        {
            return Collections.emptyList();
        }

        MojoExecution forkedExecution = new MojoExecution( forkedMojoDescriptor, forkedGoal );

        mojoExecutionConfigurator( forkedExecution ).configure( project, forkedExecution, true );

        finalizeMojoConfiguration( forkedExecution );

        calculateForkedExecutions( forkedExecution, session, project, alreadyForkedExecutions );

        return Collections.singletonList( forkedExecution );
    }
