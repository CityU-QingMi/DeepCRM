    @Test
    public void testStrictToolchainsParseException() throws Exception
    {
        ToolchainsBuildingRequest request = new DefaultToolchainsBuildingRequest();
        request.setGlobalToolchainsSource( new StringSource( "" ) );
        ToolchainsParseException parseException = new ToolchainsParseException( "MESSAGE", 4, 2 );
        when( toolchainsReader.read( any( InputStream.class ), anyMap() ) ).thenThrow( parseException );
        
        try
        {
            toolchainBuilder.build( request );
        }
        catch ( ToolchainsBuildingException e )
        {
            assertEquals( "1 problem was encountered while building the effective toolchains" + LS + 
                "[FATAL] Non-parseable toolchains (memory): MESSAGE @ line 4, column 2" + LS, e.getMessage() );
        }
    }
