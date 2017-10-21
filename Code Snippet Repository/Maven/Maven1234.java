    public void testDependencyPrecedesProjectThatUsesSpecificDependencyVersion()
        throws Exception
    {
        List<MavenProject> projects = new ArrayList<>();

        MavenProject usingProject = createProject( "group", "project", "1.0" );
        projects.add( usingProject );
        usingProject.getModel().addDependency( createDependency( "group", "dependency", "1.0" ) );

        MavenProject pluginProject = createProject( "group", "dependency", "1.0" );
        projects.add( pluginProject );

        projects = new ProjectSorter( projects ).getSortedProjects();

        assertEquals( pluginProject, projects.get( 0 ) );
        assertEquals( usingProject, projects.get( 1 ) );
    }
