    public void testDependencyManagement()
        throws Exception
    {
        File localRepo = getLocalRepositoryPath();
        File pom0 = new File( localRepo, "p0/pom.xml" );

        File pom0Basedir = pom0.getParentFile();

        File pom1 = new File( pom0Basedir, "p1/pom.xml" );

        // load everything...
        MavenProject project0 = getProjectWithDependencies( pom0 );
        MavenProject project1 = getProjectWithDependencies( pom1 );

        assertEquals( pom0Basedir, project1.getParent().getBasedir() );
        Set set = project1.getArtifacts();
        assertNotNull( "No artifacts", set );
        assertTrue( "No Artifacts", set.size() > 0 );
        Iterator iter = set.iterator();
        assertTrue( "Set size should be 4, is " + set.size(), set.size() == 4 );

        while ( iter.hasNext() )
        {
            Artifact artifact = (Artifact) iter.next();
            System.out.println( "Artifact: " + artifact.getDependencyConflictId() + " " + artifact.getVersion()
              + " Optional=" + ( artifact.isOptional() ? "true" : "false" ) );
            assertTrue( "Incorrect version for " + artifact.getDependencyConflictId(),
                        artifact.getVersion().equals( "1.0" ) );
        }

    }
