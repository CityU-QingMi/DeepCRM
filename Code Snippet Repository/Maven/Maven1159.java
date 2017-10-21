    public void testCloneWithDependencyManagement()
        throws Exception
    {
        File f = getFileForClasspathResource( "dependencyManagement-pom.xml" );
        MavenProject projectToClone = getProjectWithDependencies( f );
        DependencyManagement dep = projectToClone.getDependencyManagement();
        assertNotNull( "No dependencyManagement", dep );
        List<?> list = dep.getDependencies();
        assertNotNull( "No dependencies", list );
        assertTrue( "Empty dependency list", !list.isEmpty() );

        Map<?, ?> map = projectToClone.getManagedVersionMap();
        assertNotNull( "No ManagedVersionMap", map );
        assertTrue( "ManagedVersionMap is empty", !map.isEmpty() );

        MavenProject clonedProject = projectToClone.clone();
        assertEquals( "maven-core", clonedProject.getArtifactId() );
        Map<?, ?> clonedMap = clonedProject.getManagedVersionMap();
        assertNotNull( "ManagedVersionMap not copied", clonedMap );
        assertTrue( "ManagedVersionMap is empty", !clonedMap.isEmpty() );
        assertTrue( "ManagedVersionMap does not contain test key",
                    clonedMap.containsKey( "maven-test:maven-test-b:jar" ) );
    }
