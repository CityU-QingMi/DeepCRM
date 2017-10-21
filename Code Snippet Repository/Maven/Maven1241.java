    public void testMatchingIdsAndDifferentVersions()
        throws CycleDetectedException, DuplicateProjectException
    {
        List<MavenProject> projects = new ArrayList<>();
        MavenProject project1 = createProject( "groupId", "artifactId", "1.0" );
        projects.add( project1 );
        MavenProject project2 = createProject( "groupId", "artifactId", "2.0" );
        projects.add( project2 );

        projects = new ProjectSorter( projects ).getSortedProjects();
        assertEquals( project1, projects.get( 0 ) );
        assertEquals( project2, projects.get( 1 ) );
    }
