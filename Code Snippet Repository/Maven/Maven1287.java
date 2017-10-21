    @Test
    public void testBuildRequestWithGlobalToolchains()
        throws Exception
    {
        ToolchainsBuildingRequest request = new DefaultToolchainsBuildingRequest();
        request.setGlobalToolchainsSource( new StringSource( "" ) );

        PersistedToolchains globalResult = new PersistedToolchains();
        ToolchainModel toolchain = new ToolchainModel();
        toolchain.setType( "TYPE" );
        toolchain.addProvide( "key", "global_value" );
        globalResult.addToolchain(  toolchain );
        when( toolchainsReader.read( any( InputStream.class ), anyMap() ) ).thenReturn( globalResult );

        ToolchainsBuildingResult result = toolchainBuilder.build( request );
        assertNotNull( result.getEffectiveToolchains() );
        assertEquals( 1, result.getEffectiveToolchains().getToolchains().size() );
        assertEquals( "TYPE", result.getEffectiveToolchains().getToolchains().get(0).getType() );
        assertEquals( "global_value", result.getEffectiveToolchains().getToolchains().get(0).getProvides().getProperty( "key" ) );
        assertNotNull( result.getProblems() );
        assertEquals( 0, result.getProblems().size() );
    }
