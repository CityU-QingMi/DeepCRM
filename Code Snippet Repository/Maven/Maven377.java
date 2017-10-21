    public void testDependencyManagementExclusionsExcludeTransitively()
        throws Exception
    {
        File localRepo = getLocalRepositoryPath();

        File pom0 = new File( localRepo, "p0/pom.xml" );
        File pom0Basedir = pom0.getParentFile();
        File pom1 = new File( pom0Basedir, "p1/pom.xml" );

        // load the child project, which inherits from p0...
        MavenProject project0 = getProjectWithDependencies( pom0 );
        MavenProject project1 = getProjectWithDependencies( pom1 );

        assertNotNull("Parent is null", project1.getParent());
        assertEquals( pom0Basedir, project1.getParent().getBasedir() );
        Map map = project1.getArtifactMap();

        assertNotNull("No artifacts", map);
        assertTrue("No Artifacts", map.size() > 0);
        assertTrue("Set size should be 2, is " + map.size(), map.size() == 2);

        assertTrue("maven-test:t09-a is not in the project", map.containsKey( "maven-test:t09-a" ));
        assertTrue("maven-test:t09-b is not in the project", map.containsKey( "maven-test:t09-b" ));
        assertFalse("maven-test:t09-c is in the project", map.containsKey( "maven-test:t09-c" ));
    }
