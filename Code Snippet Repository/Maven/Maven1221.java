    public void testResolveDependencySuccessfullyResolvesExistingDependencyUsingHighestVersion() throws Exception
    {
        final Dependency dependency = new Dependency();
        dependency.setGroupId( "org.apache" );
        dependency.setArtifactId( "apache" );
        dependency.setVersion( "(,2.0)" );

        assertNotNull( this.newModelResolver().resolveModel( dependency ) );
        assertEquals( "1", dependency.getVersion() );
    }
