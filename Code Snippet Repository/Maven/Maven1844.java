    public void testResolveDependencyThrowsUnresolvableModelExceptionWhenUsingRangesWithoutUpperBound() throws Exception
    {
        final Dependency dependency = new Dependency();
        dependency.setGroupId( "ut.simple" );
        dependency.setArtifactId( "artifact" );
        dependency.setVersion( "[1.0,)" );

        try
        {
            this.newModelResolver().resolveModel( dependency );
            fail( "Expected 'UnresolvableModelException' not thrown." );
        }
        catch ( final UnresolvableModelException e )
        {
            assertEquals( "The requested dependency version range '[1.0,)' does not specify an upper bound",
                          e.getMessage() );

        }
    }
