    public void testResolveParentSuccessfullyResolvesExistingParentUsingHighestVersion() throws Exception
    {
        final Parent parent = new Parent();
        parent.setGroupId( "ut.simple" );
        parent.setArtifactId( "artifact" );
        parent.setVersion( "(,2.0)" );

        assertNotNull( this.newModelResolver().resolveModel( parent ) );
        assertEquals( "1.0", parent.getVersion() );
    }
