    public void testShouldNotFailWhenManagedPluginDepReferencesCurrentProject()
        throws CycleDetectedException, DuplicateProjectException
    {
        MavenProject project = createProject( "group", "artifact", "1.0" );

        Build build = project.getModel().getBuild();

        PluginManagement pMgmt = new PluginManagement();

        Plugin plugin = createPlugin( "other.group", "other-artifact", "1.0" );

        Dependency dep = createDependency( "group", "artifact", "1.0" );

        plugin.addDependency( dep );

        pMgmt.addPlugin( plugin );

        build.setPluginManagement( pMgmt );

        new ProjectSorter( Collections.singletonList( project ) );
    }
