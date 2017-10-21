    @Test
    public void testMergeJdk()
        throws Exception
    {
        try ( InputStream isDominant = ToolchainModel.class.getResourceAsStream( "toolchains-jdks.xml" );
              InputStream isRecessive = ToolchainModel.class.getResourceAsStream( "toolchains-jdks.xml" ) )
        {
            PersistedToolchains dominant = reader.read( isDominant );
            PersistedToolchains recessive = reader.read( isRecessive );
            assertEquals( 2, dominant.getToolchains().size() );

            merger.merge( dominant, recessive, TrackableBase.USER_LEVEL );
            assertEquals( 2, dominant.getToolchains().size() );
        }
    }
