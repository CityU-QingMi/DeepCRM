    public void testClone()
        throws Exception
    {
        File f = getFileForClasspathResource( "canonical-pom.xml" );
        MavenProject projectToClone = getProject( f );

        MavenProject clonedProject = projectToClone.clone();
        assertEquals( "maven-core", clonedProject.getArtifactId() );
        Map<?, ?> clonedMap = clonedProject.getManagedVersionMap();
        assertNotNull( "ManagedVersionMap not copied", clonedMap );
        assertTrue( "ManagedVersionMap is not empty", clonedMap.isEmpty() );
    }
