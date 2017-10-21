    public void testDependencyManagementOverridesTransitiveDependencyVersion()
        throws Exception
    {
        File localRepo = getLocalRepositoryPath();

        File pom0 = new File( localRepo, "p0/pom.xml" );
        File pom0Basedir = pom0.getParentFile();
        File pom1 = new File( pom0Basedir, "p1/pom.xml" );

        // load the child project, which inherits from p0...
        MavenProject project0 = getProjectWithDependencies( pom0 );
        MavenProject project1 = getProjectWithDependencies( pom1 );

        assertEquals( pom0Basedir, project1.getParent().getBasedir() );
        System.out.println("Project " + project1.getId() + " " + project1);
        Map map = project1.getArtifactMap();
        assertNotNull("No artifacts", map);
        assertTrue("No Artifacts", map.size() > 0);
        assertTrue("Set size should be 3, is " + map.size(), map.size() == 3);

        Artifact a = (Artifact) map.get("maven-test:t10-a");
        Artifact b = (Artifact) map.get("maven-test:t10-b");
        Artifact c = (Artifact) map.get("maven-test:t10-c");

        assertNotNull( a );
        assertNotNull( b );
        assertNotNull( c );

        // inherited from depMgmt
        System.out.println(a.getScope());
        assertTrue("Incorrect scope for " + a.getDependencyConflictId(), a.getScope().equals("test"));

        // transitive dep, overridden b depMgmt
        assertTrue("Incorrect scope for " + b.getDependencyConflictId(), b.getScope().equals("runtime"));

        // direct dep, overrides depMgmt
        assertTrue("Incorrect scope for " + c.getDependencyConflictId(), c.getScope().equals("runtime"));

    }
