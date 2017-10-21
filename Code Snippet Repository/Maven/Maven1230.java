    public void testResolveDependencyThrowsUnresolvableModelExceptionWhenUsingRangesWithoutUpperBound() throws Exception
    {
        final Dependency dependency = new Dependency();
        dependency.setGroupId( "org.apache" );
        dependency.setArtifactId( "apache" );
        dependency.setVersion( "[1,)" );

        try
        {
            this.newModelResolver().resolveModel( dependency );
            fail( "Expected 'UnresolvableModelException' not thrown." );
        }
        catch ( final UnresolvableModelException e )
        {
            assertEquals( "The requested dependency version range '[1,)' does not specify an upper bound",
                          e.getMessage() );

        }
    }
