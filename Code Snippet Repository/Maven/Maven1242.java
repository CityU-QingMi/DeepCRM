    public void testPluginDependenciesInfluenceSorting()
        throws Exception
    {
        List<MavenProject> projects = new ArrayList<>();

        MavenProject parentProject = createProject( "groupId", "parent", "1.0" );
        projects.add( parentProject );

        MavenProject declaringProject = createProject( "groupId", "declarer", "1.0" );
        declaringProject.setParent( parentProject );
        declaringProject.getModel().setParent( createParent( parentProject ) );
        projects.add( declaringProject );

        MavenProject pluginLevelDepProject = createProject( "groupId", "plugin-level-dep", "1.0" );
        pluginLevelDepProject.setParent( parentProject );
        pluginLevelDepProject.getModel().setParent( createParent( parentProject ) );
        projects.add( pluginLevelDepProject );

        MavenProject pluginProject = createProject( "groupId", "plugin", "1.0" );
        pluginProject.setParent( parentProject );
        pluginProject.getModel().setParent( createParent( parentProject ) );
        projects.add( pluginProject );

        Plugin plugin = createPlugin( pluginProject );

        plugin.addDependency( createDependency( pluginLevelDepProject ) );

        Build build = declaringProject.getModel().getBuild();

        build.addPlugin( plugin );

        projects = new ProjectSorter( projects ).getSortedProjects();

        assertEquals( parentProject, projects.get( 0 ) );

        // the order of these two is non-deterministic, based on when they're added to the reactor.
        assertTrue( projects.contains( pluginProject ) );
        assertTrue( projects.contains( pluginLevelDepProject ) );

        // the declaring project MUST be listed after the plugin and its plugin-level dep, though.
        assertEquals( declaringProject, projects.get( 3 ) );
    }
