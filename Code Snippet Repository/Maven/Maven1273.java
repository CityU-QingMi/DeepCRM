    @Test
    public void testNoModels()
    {
        MavenSession session = mock( MavenSession.class );
        MavenExecutionRequest executionRequest = new DefaultMavenExecutionRequest();
        when( session.getRequest() ).thenReturn( executionRequest );

        List<Toolchain> toolchains = toolchainManager.getToolchains( session, "unknown", null );

        assertEquals( 0, toolchains.size() );
    }
