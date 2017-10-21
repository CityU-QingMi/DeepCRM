    @Test
    public void testModelAndFactory()
    {
        MavenSession session = mock( MavenSession.class );
        MavenExecutionRequest executionRequest = new DefaultMavenExecutionRequest();
        Map<String, List<ToolchainModel>> toolchainModels = new HashMap<>();
        toolchainModels.put( "basic", Arrays.asList( new ToolchainModel(), new ToolchainModel() ) );
        toolchainModels.put( "rare", Collections.singletonList( new ToolchainModel() ) );
        executionRequest.setToolchains( toolchainModels );
        when( session.getRequest() ).thenReturn( executionRequest );

        List<Toolchain> toolchains = toolchainManager.getToolchains( session, "rare", null );

        assertEquals( 1, toolchains.size() );
    }
