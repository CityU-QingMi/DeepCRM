    private void calculateForkedExecutions( MojoExecution mojoExecution, MavenSession session, MavenProject project,
                                            Collection<MojoDescriptor> alreadyForkedExecutions )
        throws MojoNotFoundException, PluginNotFoundException, PluginResolutionException,
        PluginDescriptorParsingException, NoPluginFoundForPrefixException, InvalidPluginDescriptorException,
        LifecyclePhaseNotFoundException, LifecycleNotFoundException, PluginVersionResolutionException
    {
        MojoDescriptor mojoDescriptor = mojoExecution.getMojoDescriptor();

        if ( !mojoDescriptor.isForking() )
        {
            return;
        }

        if ( !alreadyForkedExecutions.add( mojoDescriptor ) )
        {
            return;
        }

        List<MavenProject> forkedProjects =
            LifecycleDependencyResolver.getProjects( project, session, mojoDescriptor.isAggregator() );

        for ( MavenProject forkedProject : forkedProjects )
        {
            if ( forkedProject != project )
            {
                lifecyclePluginResolver.resolveMissingPluginVersions( forkedProject, session );
            }

            List<MojoExecution> forkedExecutions;

            if ( StringUtils.isNotEmpty( mojoDescriptor.getExecutePhase() ) )
            {
                forkedExecutions =
                    calculateForkedLifecycle( mojoExecution, session, forkedProject, alreadyForkedExecutions );
            }
            else
            {
                forkedExecutions = calculateForkedGoal( mojoExecution, session, forkedProject,
                                                        alreadyForkedExecutions );
            }

            mojoExecution.setForkedExecutions( BuilderCommon.getKey( forkedProject ), forkedExecutions );
        }

        alreadyForkedExecutions.remove( mojoDescriptor );
    }
