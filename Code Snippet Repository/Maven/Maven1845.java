    public void testResolveDependencySuccessfullyResolvesExistingDependencyWithoutRange() throws Exception
    {
        final Dependency dependency = new Dependency();
        dependency.setGroupId( "ut.simple" );
        dependency.setArtifactId( "artifact" );
        dependency.setVersion( "1.0" );

        assertNotNull( this.newModelResolver().resolveModel( dependency ) );
        assertEquals( "1.0", dependency.getVersion() );
    }
