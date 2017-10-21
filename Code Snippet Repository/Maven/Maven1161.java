    public void testCloneWithDistributionManagement()
        throws Exception
    {

        File f = getFileForClasspathResource( "distributionManagement-pom.xml" );
        MavenProject projectToClone = getProject( f );

        MavenProject clonedProject = projectToClone.clone();
        assertNotNull( "clonedProject - distributionManagement", clonedProject.getDistributionManagementArtifactRepository() );
    }
