    public void testResolveParentThrowsUnresolvableModelExceptionWhenUsingRangesWithoutUpperBound() throws Exception
    {
        final Parent parent = new Parent();
        parent.setGroupId( "ut.simple" );
        parent.setArtifactId( "artifact" );
        parent.setVersion( "[1.0,)" );

        try
        {
            this.newModelResolver().resolveModel( parent );
            fail( "Expected 'UnresolvableModelException' not thrown." );
        }
        catch ( final UnresolvableModelException e )
        {
            assertEquals( "The requested parent version range '[1.0,)' does not specify an upper bound",
                          e.getMessage() );

        }
    }
