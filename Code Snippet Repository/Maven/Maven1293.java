    @Test
    public void testMergeJdkExtra()
        throws Exception
    {
        try ( InputStream jdksIS = ToolchainModel.class.getResourceAsStream( "toolchains-jdks.xml" );
              InputStream jdksExtraIS = ToolchainModel.class.getResourceAsStream( "toolchains-jdks-extra.xml" ) )
        {
            PersistedToolchains jdks = reader.read( jdksIS );
            PersistedToolchains jdksExtra = reader.read( jdksExtraIS );
            assertEquals( 2, jdks.getToolchains().size() );

            merger.merge( jdks, jdksExtra, TrackableBase.USER_LEVEL );
            assertEquals( 4, jdks.getToolchains().size() );
            assertEquals( 2, jdksExtra.getToolchains().size() );
        }
        try ( InputStream jdksIS = ToolchainModel.class.getResourceAsStream( "toolchains-jdks.xml" );
              InputStream jdksExtraIS = ToolchainModel.class.getResourceAsStream( "toolchains-jdks-extra.xml" ) )
        {
            PersistedToolchains jdks = reader.read( jdksIS );
            PersistedToolchains jdksExtra = reader.read( jdksExtraIS );
            assertEquals( 2, jdks.getToolchains().size() );

            // switch dominant with recessive
            merger.merge( jdksExtra, jdks, TrackableBase.USER_LEVEL );
            assertEquals( 4, jdksExtra.getToolchains().size() );
            assertEquals( 2, jdks.getToolchains().size() );
        }
    }
