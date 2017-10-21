    public void testMatchingIdsAndVersions()
        throws CycleDetectedException
    {
        List<MavenProject> projects = new ArrayList<>();
        MavenProject project1 = createProject( "groupId", "artifactId", "1.0" );
        projects.add( project1 );
        MavenProject project2 = createProject( "groupId", "artifactId", "1.0" );
        projects.add( project2 );

        try
        {
            projects = new ProjectSorter( projects ).getSortedProjects();
            fail( "Duplicate projects should fail" );
        }
        catch ( DuplicateProjectException e )
        {
            // expected
            assertTrue( true );
        }
    }
