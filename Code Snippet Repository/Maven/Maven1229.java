    public void testResolveDependencyThrowsUnresolvableModelExceptionWhenNoMatchingVersionFound() throws Exception
    {
        final Dependency dependency = new Dependency();
        dependency.setGroupId( "org.apache" );
        dependency.setArtifactId( "apache" );
        dependency.setVersion( "[2.0,2.1)" );

        try
        {
            this.newModelResolver().resolveModel( dependency );
            fail( "Expected 'UnresolvableModelException' not thrown." );
        }
        catch ( final UnresolvableModelException e )
        {
            assertEquals( "No versions matched the requested dependency version range '[2.0,2.1)'",
                          e.getMessage() );

        }
    }
