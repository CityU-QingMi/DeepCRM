    public void testVersionlessManagedDependency()
        throws Exception
    {
        File pomFile = new File( "src/test/resources/projects/versionless-managed-dependency.xml" );
        MavenSession mavenSession = createMavenSession( null );
        ProjectBuildingRequest configuration = new DefaultProjectBuildingRequest();
        configuration.setRepositorySession( mavenSession.getRepositorySession() );

        try
        {
            lookup( org.apache.maven.project.ProjectBuilder.class ).build( pomFile, configuration );
            fail();
        }
        catch ( ProjectBuildingException e )
        {
            // this is expected
        }
    }
