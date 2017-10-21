    @Test
    public void testModelNoFactory()
    {
        MavenSession session = mock( MavenSession.class );
        MavenExecutionRequest executionRequest = new DefaultMavenExecutionRequest();
        Map<String, List<ToolchainModel>> toolchainModels = new HashMap<>();
        toolchainModels.put( "unknown", Collections.singletonList( new ToolchainModel() ) );
        executionRequest.setToolchains( toolchainModels );
        when( session.getRequest() ).thenReturn( executionRequest );

        List<Toolchain> toolchains = toolchainManager.getToolchains( session, "unknown", null );

        assertEquals( 0, toolchains.size() );
        verify( logger ).error( "Missing toolchain factory for type: unknown. Possibly caused by misconfigured project." );
    }
