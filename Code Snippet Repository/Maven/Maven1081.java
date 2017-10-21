    public MavenExecutionPlan calculateExecutionPlan( MavenSession session, MavenProject project, List<Object> tasks,
                                                      boolean setup )
        throws PluginNotFoundException, PluginResolutionException, LifecyclePhaseNotFoundException,
        PluginDescriptorParsingException, MojoNotFoundException, InvalidPluginDescriptorException,
        NoPluginFoundForPrefixException, LifecycleNotFoundException, PluginVersionResolutionException
    {
        if ( project.equals( ProjectDependencyGraphStub.A ) )
        {
            return getProjectAExceutionPlan();
        }
        if ( project.equals( ProjectDependencyGraphStub.B ) )
        {
            return getProjectBExecutionPlan();
        }
        // The remaining are basically "for future expansion"
        List<MojoExecution> me = new ArrayList<>();
        me.add( createMojoExecution( "resources", "default-resources", PROCESS_RESOURCES ) );
        me.add( createMojoExecution( "compile", "default-compile", COMPILE ) );
        return createExecutionPlan( project, me );
    }
