    @Test
    public void testMergeJdkExtend()
        throws Exception
    {
        try ( InputStream jdksIS = ToolchainModel.class.getResourceAsStream( "toolchains-jdks.xml" );
              InputStream jdksExtendIS = ToolchainModel.class.getResourceAsStream( "toolchains-jdks-extend.xml" ) )
        {
            PersistedToolchains jdks = reader.read( jdksIS );
            PersistedToolchains jdksExtend = reader.read( jdksExtendIS );
            assertEquals( 2, jdks.getToolchains().size() );

            merger.merge( jdks, jdksExtend, TrackableBase.USER_LEVEL );
            assertEquals( 2, jdks.getToolchains().size() );
            Xpp3Dom config0 = (Xpp3Dom) jdks.getToolchains().get( 0 ).getConfiguration();
            assertEquals( "lib/tools.jar", config0.getChild( "toolsJar" ).getValue() );
            assertEquals( 2, config0.getChildCount() );
            Xpp3Dom config1 = (Xpp3Dom) jdks.getToolchains().get( 1 ).getConfiguration();
            assertEquals( 2, config1.getChildCount() );
            assertEquals( "lib/classes.jar", config1.getChild( "toolsJar" ).getValue() );
            assertEquals( 2, jdksExtend.getToolchains().size() );
        }
        try ( InputStream jdksIS = ToolchainModel.class.getResourceAsStream( "toolchains-jdks.xml" );
              InputStream jdksExtendIS = ToolchainModel.class.getResourceAsStream( "toolchains-jdks-extend.xml" ) )
        {
            PersistedToolchains jdks = reader.read( jdksIS );
            PersistedToolchains jdksExtend = reader.read( jdksExtendIS );
            assertEquals( 2, jdks.getToolchains().size() );

            // switch dominant with recessive
            merger.merge( jdksExtend, jdks, TrackableBase.USER_LEVEL );
            assertEquals( 2, jdksExtend.getToolchains().size() );
            Xpp3Dom config0 = (Xpp3Dom) jdksExtend.getToolchains().get( 0 ).getConfiguration();
            assertEquals( "lib/tools.jar", config0.getChild( "toolsJar" ).getValue() );
            assertEquals( 2, config0.getChildCount() );
            Xpp3Dom config1 = (Xpp3Dom) jdksExtend.getToolchains().get( 1 ).getConfiguration();
            assertEquals( 2, config1.getChildCount() );
            assertEquals( "lib/classes.jar", config1.getChild( "toolsJar" ).getValue() );
            assertEquals( 2, jdks.getToolchains().size() );
        }
    }
