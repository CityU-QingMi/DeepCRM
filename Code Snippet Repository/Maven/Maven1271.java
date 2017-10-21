    @SuppressWarnings( "" )
    @Test( expected = MisconfiguredToolchainException.class )
    public void testMisconfiguredToolchain()
        throws Exception
    {
        // prepare
        MavenSession session = mock( MavenSession.class );
        MavenExecutionRequest req = new DefaultMavenExecutionRequest();
        when( session.getRequest() ).thenReturn( req );
        when(toolchainFactory_basicType.createDefaultToolchain()).thenThrow( MisconfiguredToolchainException.class );

        // execute
        toolchainManager.getToolchainsForType( "basic", session );
        
        // verify
        fail( "Should exit with a MisconfiguredToolchainException" );
    }
