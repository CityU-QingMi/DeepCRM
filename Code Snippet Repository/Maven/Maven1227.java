    public void testResolveParentSuccessfullyResolvesExistingParentUsingHighestVersion() throws Exception
    {
        final Parent parent = new Parent();
        parent.setGroupId( "org.apache" );
        parent.setArtifactId( "apache" );
        parent.setVersion( "(,2.0)" );

        assertNotNull( this.newModelResolver().resolveModel( parent ) );
        assertEquals( "1", parent.getVersion() );
    }
