    @Override
    public void setupMojoExecution( MavenSession session, MavenProject project, MojoExecution mojoExecution )
        throws PluginNotFoundException, PluginResolutionException, PluginDescriptorParsingException,
        MojoNotFoundException, InvalidPluginDescriptorException, NoPluginFoundForPrefixException,
        LifecyclePhaseNotFoundException, LifecycleNotFoundException, PluginVersionResolutionException
    {
        MojoDescriptor mojoDescriptor = mojoExecution.getMojoDescriptor();

        if ( mojoDescriptor == null )
        {
            mojoDescriptor =
                pluginManager.getMojoDescriptor( mojoExecution.getPlugin(), mojoExecution.getGoal(),
                                                 project.getRemotePluginRepositories(),
                                                 session.getRepositorySession() );

            mojoExecution.setMojoDescriptor( mojoDescriptor );
        }

        mojoExecutionConfigurator( mojoExecution ).configure( project,
                                                              mojoExecution,
                                                        MojoExecution.Source.CLI.equals( mojoExecution.getSource() ) );

        finalizeMojoConfiguration( mojoExecution );

        calculateForkedExecutions( mojoExecution, session, project, new HashSet<MojoDescriptor>() );
    }
