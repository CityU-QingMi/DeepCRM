    public void testPluginDependenciesInfluenceSorting_DeclarationInParent()
        throws Exception
    {
        List<MavenProject> projects = new ArrayList<>();

        MavenProject parentProject = createProject( "groupId", "parent-declarer", "1.0" );
        projects.add( parentProject );

        MavenProject pluginProject = createProject( "groupId", "plugin", "1.0" );
        pluginProject.setParent( parentProject );
        pluginProject.getModel().setParent( createParent( parentProject ) );
        projects.add( pluginProject );

        MavenProject pluginLevelDepProject = createProject( "groupId", "plugin-level-dep", "1.0" );
        pluginLevelDepProject.setParent( parentProject );
        pluginLevelDepProject.getModel().setParent( createParent( parentProject ) );
        projects.add( pluginLevelDepProject );

        Plugin plugin = createPlugin( pluginProject );

        plugin.addDependency( createDependency( pluginLevelDepProject ) );

        Build build = parentProject.getModel().getBuild();

        build.addPlugin( plugin );

        projects = new ProjectSorter( projects ).getSortedProjects();

        System.out.println( projects );

        assertEquals( parentProject, projects.get( 0 ) );

        // the order of these two is non-deterministic, based on when they're added to the reactor.
        assertTrue( projects.contains( pluginProject ) );
        assertTrue( projects.contains( pluginLevelDepProject ) );
    }
