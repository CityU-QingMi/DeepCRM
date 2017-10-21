    @Test
    public void testBuildRequestWithBothToolchains()
        throws Exception
    {
        ToolchainsBuildingRequest request = new DefaultToolchainsBuildingRequest();
        request.setGlobalToolchainsSource( new StringSource( "" ) );
        request.setUserToolchainsSource( new StringSource( "" ) );

        PersistedToolchains userResult = new PersistedToolchains();
        ToolchainModel userToolchain = new ToolchainModel();
        userToolchain.setType( "TYPE" );
        userToolchain.addProvide( "key", "user_value" );
        userResult.addToolchain(  userToolchain );

        PersistedToolchains globalResult = new PersistedToolchains();
        ToolchainModel globalToolchain = new ToolchainModel();
        globalToolchain.setType( "TYPE" );
        globalToolchain.addProvide( "key", "global_value" );
        globalResult.addToolchain(  globalToolchain );
        when( toolchainsReader.read( any( InputStream.class ), anyMap() ) ).thenReturn( globalResult ).thenReturn( userResult );

        ToolchainsBuildingResult result = toolchainBuilder.build( request );
        assertNotNull( result.getEffectiveToolchains() );
        assertEquals( 2, result.getEffectiveToolchains().getToolchains().size() );
        assertEquals( "TYPE", result.getEffectiveToolchains().getToolchains().get(0).getType() );
        assertEquals( "user_value", result.getEffectiveToolchains().getToolchains().get(0).getProvides().getProperty( "key" ) );
        assertEquals( "TYPE", result.getEffectiveToolchains().getToolchains().get(1).getType() );
        assertEquals( "global_value", result.getEffectiveToolchains().getToolchains().get(1).getProvides().getProperty( "key" ) );
        assertNotNull( result.getProblems() );
        assertEquals( 0, result.getProblems().size() );
    }
