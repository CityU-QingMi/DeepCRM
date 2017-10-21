    public void testShouldNotFailWhenPluginDepReferencesCurrentProject()
        throws CycleDetectedException, DuplicateProjectException
    {
        MavenProject project = createProject( "group", "artifact", "1.0" );

        Build build = project.getModel().getBuild();

        Plugin plugin = createPlugin( "other.group", "other-artifact", "1.0" );

        Dependency dep = createDependency( "group", "artifact", "1.0" );

        plugin.addDependency( dep );

        build.addPlugin( plugin );

        new ProjectSorter( Collections.singletonList( project ) );
    }
