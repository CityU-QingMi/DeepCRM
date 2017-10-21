    @Test
    public void testToolchainsForConfiguredType()
        throws Exception
    {
        // prepare
        MavenSession session = mock( MavenSession.class );
        MavenExecutionRequest req = new DefaultMavenExecutionRequest();
        when( session.getRequest() ).thenReturn( req );
        Map<String, List<ToolchainModel>> groupedToolchains = new HashMap<>();
        req.setToolchains( groupedToolchains );

        List<ToolchainModel> basicToolchains = new ArrayList<>();
        ToolchainModel basicToolchainModel = new ToolchainModel();
        basicToolchainModel.setType( "basic" );
        basicToolchains.add( basicToolchainModel );
        basicToolchains.add( basicToolchainModel );
        groupedToolchains.put( "basic", basicToolchains );

        List<ToolchainModel> rareToolchains = new ArrayList<>();
        ToolchainModel rareToolchainModel = new ToolchainModel();
        rareToolchainModel.setType( "rare" );
        rareToolchains.add( rareToolchainModel );
        groupedToolchains.put( "rare", rareToolchains );

        // execute
        ToolchainPrivate[] toolchains = toolchainManager.getToolchainsForType( "basic", session );

        // verify
        verify( logger, never() ).error( anyString() );
        assertEquals( 2, toolchains.length );
    }
