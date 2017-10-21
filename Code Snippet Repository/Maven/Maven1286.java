    @Test
    public void testBuildRequestWithUserToolchains()
        throws Exception
    {
        ToolchainsBuildingRequest request = new DefaultToolchainsBuildingRequest();
        request.setUserToolchainsSource( new StringSource( "" ) );

        PersistedToolchains userResult = new PersistedToolchains();
        ToolchainModel toolchain = new ToolchainModel();
        toolchain.setType( "TYPE" );
        toolchain.addProvide( "key", "user_value" );
        userResult.addToolchain(  toolchain );
        when( toolchainsReader.read( any( InputStream.class ), anyMap() ) ).thenReturn( userResult );

        ToolchainsBuildingResult result = toolchainBuilder.build( request );
        assertNotNull( result.getEffectiveToolchains() );
        assertEquals( 1, result.getEffectiveToolchains().getToolchains().size() );
        assertEquals( "TYPE", result.getEffectiveToolchains().getToolchains().get(0).getType() );
        assertEquals( "user_value", result.getEffectiveToolchains().getToolchains().get(0).getProvides().getProperty( "key" ) );
        assertNotNull( result.getProblems() );
        assertEquals( 0, result.getProblems().size() );
    }
