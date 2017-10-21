    public static MavenExecutionPlan getProjectBExecutionPlan()
        throws PluginNotFoundException, PluginResolutionException, LifecyclePhaseNotFoundException,
        PluginDescriptorParsingException, MojoNotFoundException, InvalidPluginDescriptorException,
        NoPluginFoundForPrefixException, LifecycleNotFoundException, PluginVersionResolutionException
    {
        List<MojoExecution> me = new ArrayList<>();
        me.add( createMojoExecution( "enforce", "enforce-versions", VALIDATE ) );
        me.add( createMojoExecution( "resources", "default-resources", PROCESS_RESOURCES ) );
        me.add( createMojoExecution( "compile", "default-compile", COMPILE ) );
        me.add( createMojoExecution( "testResources", "default-testResources", PROCESS_TEST_RESOURCES ) );
        me.add( createMojoExecution( "testCompile", "default-testCompile", TEST_COMPILE ) );
        me.add( createMojoExecution( "test", "default-test", TEST ) );
        return createExecutionPlan( ProjectDependencyGraphStub.B.getExecutionProject(), me );
    }
