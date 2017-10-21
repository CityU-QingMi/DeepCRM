    private Map<String, List<MojoExecution>> calculateLifecycleMappings( MavenSession session, MavenProject project,
                                                                         String lifecyclePhase )
        throws LifecyclePhaseNotFoundException, PluginNotFoundException, PluginResolutionException,
        PluginDescriptorParsingException, MojoNotFoundException, InvalidPluginDescriptorException
    {
/**/
/**/
/**/

        Lifecycle lifecycle = defaultLifeCycles.get( lifecyclePhase );

        if ( lifecycle == null )
        {
            throw new LifecyclePhaseNotFoundException( "Unknown lifecycle phase \"" + lifecyclePhase
                + "\". You must specify a valid lifecycle phase" + " or a goal in the format <plugin-prefix>:<goal> or"
                + " <plugin-group-id>:<plugin-artifact-id>[:<plugin-version>]:<goal>. Available lifecycle phases are: "
                + defaultLifeCycles.getLifecyclePhaseList() + ".", lifecyclePhase );
        }

        LifecycleMappingDelegate delegate;
        if ( Arrays.binarySearch( DefaultLifecycles.STANDARD_LIFECYCLES, lifecycle.getId() ) >= 0 )
        {
            delegate = standardDelegate;
        }
        else
        {
            delegate = delegates.get( lifecycle.getId() );
            if ( delegate == null )
            {
                delegate = standardDelegate;
            }
        }

        return delegate.calculateLifecycleMappings( session, project, lifecycle, lifecyclePhase );
    }
