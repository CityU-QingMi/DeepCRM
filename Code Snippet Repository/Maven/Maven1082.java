    public static MavenExecutionPlan getProjectAExceutionPlan()
        throws PluginNotFoundException, PluginResolutionException, LifecyclePhaseNotFoundException,
        PluginDescriptorParsingException, MojoNotFoundException, InvalidPluginDescriptorException,
        NoPluginFoundForPrefixException, LifecycleNotFoundException, PluginVersionResolutionException
    {
        List<MojoExecution> me = new ArrayList<>();
        me.add( createMojoExecution( "initialize", "default-initialize", INITIALIZE ) );
        me.add( createMojoExecution( "resources", "default-resources", PROCESS_RESOURCES ) );
        me.add( createMojoExecution( "compile", "default-compile", COMPILE ) );
        me.add( createMojoExecution( "testResources", "default-testResources", PROCESS_TEST_RESOURCES ) );
        me.add( createMojoExecution( "testCompile", "default-testCompile", TEST_COMPILE ) );
        me.add( createMojoExecution( "test", "default-test", TEST ) );
        me.add( createMojoExecution( "war", "default-war", PACKAGE ) );
        me.add( createMojoExecution( "install", "default-install", INSTALL ) );
        return createExecutionPlan( ProjectDependencyGraphStub.A.getExecutionProject(), me );
    }
