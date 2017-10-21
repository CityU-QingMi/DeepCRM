    public List<MojoExecution> calculateMojoExecutions( MavenSession session, MavenProject project, List<Object> tasks )
        throws PluginNotFoundException, PluginResolutionException, PluginDescriptorParsingException,
        MojoNotFoundException, NoPluginFoundForPrefixException, InvalidPluginDescriptorException,
        PluginVersionResolutionException, LifecyclePhaseNotFoundException
    {
        final List<MojoExecution> mojoExecutions = new ArrayList<>();

        for ( Object task : tasks )
        {
            if ( task instanceof GoalTask )
            {
                String pluginGoal = ( (GoalTask) task ).pluginGoal;

                String executionId = "default-cli";
                int executionIdx = pluginGoal.indexOf( '@' );
                if ( executionIdx > 0 )
                {
                    executionId = pluginGoal.substring( executionIdx + 1 );
                }

                MojoDescriptor mojoDescriptor = mojoDescriptorCreator.getMojoDescriptor( pluginGoal, session, project );

                MojoExecution mojoExecution = new MojoExecution( mojoDescriptor, executionId,
                                                                 MojoExecution.Source.CLI );

                mojoExecutions.add( mojoExecution );
            }
            else if ( task instanceof LifecycleTask )
            {
                String lifecyclePhase = ( (LifecycleTask) task ).getLifecyclePhase();

                Map<String, List<MojoExecution>> phaseToMojoMapping =
                    calculateLifecycleMappings( session, project, lifecyclePhase );

                for ( List<MojoExecution> mojoExecutionsFromLifecycle : phaseToMojoMapping.values() )
                {
                    mojoExecutions.addAll( mojoExecutionsFromLifecycle );
                }
            }
            else
            {
                throw new IllegalStateException( "unexpected task " + task );
            }
        }
        return mojoExecutions;
    }
