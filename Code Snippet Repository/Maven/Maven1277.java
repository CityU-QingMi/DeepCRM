    @Test
    public void testRequirements()
        throws Exception
    {
        MavenSession session = mock( MavenSession.class );
        MavenExecutionRequest executionRequest = new DefaultMavenExecutionRequest();
        Map<String, List<ToolchainModel>> toolchainModels = new HashMap<>();
        toolchainModels.put( "basic", Arrays.asList( new ToolchainModel(), new ToolchainModel() ) );
        toolchainModels.put( "rare", Collections.singletonList( new ToolchainModel() ) );
        executionRequest.setToolchains( toolchainModels );
        when( session.getRequest() ).thenReturn( executionRequest );
        ToolchainPrivate basicPrivate = mock( ToolchainPrivate.class );
        when( basicPrivate.matchesRequirements( anyMap() ) ).thenReturn( false ).thenReturn( true );
        when( toolchainFactory_basicType.createToolchain( isA( ToolchainModel.class ) ) ).thenReturn( basicPrivate );

        List<Toolchain> toolchains =
            toolchainManager.getToolchains( session, "basic", Collections.singletonMap( "key", "value" ) );

        assertEquals( 1, toolchains.size() );
    }
