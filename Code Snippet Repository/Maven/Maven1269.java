    @Test
    public void testToolchainsForUnknownType()
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
        ToolchainPrivate[] toolchains = toolchainManager.getToolchainsForType( "unknown", session );

        // verify
        verify( logger ).error( "Missing toolchain factory for type: unknown. Possibly caused by misconfigured project." );
        assertEquals( 0, toolchains.length );
    }
