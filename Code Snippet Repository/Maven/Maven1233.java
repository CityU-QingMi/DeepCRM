    public void testPluginVersionsAreConsidered()
        throws Exception
    {
        List<MavenProject> projects = new ArrayList<>();

        MavenProject pluginProjectA = createProject( "group", "plugin-a", "2.0-SNAPSHOT" );
        projects.add( pluginProjectA );
        pluginProjectA.getModel().getBuild().addPlugin( createPlugin( "group", "plugin-b", "1.0" ) );

        MavenProject pluginProjectB = createProject( "group", "plugin-b", "2.0-SNAPSHOT" );
        projects.add( pluginProjectB );
        pluginProjectB.getModel().getBuild().addPlugin( createPlugin( "group", "plugin-a", "1.0" ) );

        projects = new ProjectSorter( projects ).getSortedProjects();

        assertTrue( projects.contains( pluginProjectA ) );
        assertTrue( projects.contains( pluginProjectB ) );
    }
