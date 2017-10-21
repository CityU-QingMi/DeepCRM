    @Test
    public void testIOException() throws Exception
    {
        ToolchainsBuildingRequest request = new DefaultToolchainsBuildingRequest();
        request.setGlobalToolchainsSource( new StringSource( "", "LOCATION" ) );
        IOException ioException = new IOException( "MESSAGE" );
        when( toolchainsReader.read( any( InputStream.class ), anyMap() ) ).thenThrow( ioException );
        
        try
        {
            toolchainBuilder.build( request );
        }
        catch ( ToolchainsBuildingException e )
        {
            assertEquals( "1 problem was encountered while building the effective toolchains" + LS + 
                "[FATAL] Non-readable toolchains LOCATION: MESSAGE" + LS, e.getMessage() );
        }
    }
