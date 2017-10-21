    @Test
    public void testToolchainsForAvailableType()
        throws Exception
    {
        // prepare
        MavenSession session = mock( MavenSession.class );
        MavenExecutionRequest req = new DefaultMavenExecutionRequest();
        when( session.getRequest() ).thenReturn( req );

        ToolchainPrivate basicToolchain = mock( ToolchainPrivate.class );
        when( toolchainFactory_basicType.createDefaultToolchain() ).thenReturn( basicToolchain );
        ToolchainPrivate rareToolchain = mock( ToolchainPrivate.class );
        when( toolchainFactory_rareType.createDefaultToolchain() ).thenReturn( rareToolchain );

        // execute
        ToolchainPrivate[] toolchains = toolchainManager.getToolchainsForType( "basic", session );

        // verify
        verify( logger, never() ).error( anyString() );
        assertEquals( 1, toolchains.length );
    }
