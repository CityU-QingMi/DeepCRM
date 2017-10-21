    public void testDependencyManagementExclusionDoesNotOverrideGloballyForTransitives()
        throws Exception
    {
        File localRepo = getLocalRepositoryPath();

        File pom0 = new File( localRepo, "p0/pom.xml" );
        File pom0Basedir = pom0.getParentFile();
        File pom2 = new File( pom0Basedir, "p2/pom.xml" );

        // load the child project, which inherits from p0...
        MavenProject project0 = getProjectWithDependencies( pom0 );
        MavenProject project2 = getProjectWithDependencies( pom2 );

        assertEquals( pom0Basedir, project2.getParent().getBasedir() );
        Map map = project2.getArtifactMap();
        assertNotNull( "No artifacts", map );
        assertTrue( "No Artifacts", map.size() > 0 );
        assertTrue( "Set size should be 4, is " + map.size(), map.size() == 4 );

        assertTrue( "maven-test:t09-a is not in the project", map.containsKey( "maven-test:t09-a" ) );
        assertTrue( "maven-test:t09-b is not in the project", map.containsKey( "maven-test:t09-b" ) );
        assertTrue( "maven-test:t09-c is not in the project", map.containsKey( "maven-test:t09-c" ) );
        assertTrue( "maven-test:t09-d is not in the project", map.containsKey( "maven-test:t09-d" ) );
    }
