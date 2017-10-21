    @Test
    public void testEquals()
        throws Exception
    {
        try ( InputStream jdksIS = ToolchainModel.class.getResourceAsStream( "toolchains-jdks.xml" );
              InputStream jdksExtraIS = ToolchainModel.class.getResourceAsStream( "toolchains-jdks-extra.xml" ) )
        {
            PersistedToolchains jdks = reader.read( jdksIS );
            PersistedToolchains jdksExtra = reader.read( jdksExtraIS );

            DefaultToolchain tc1 = new DefaultJavaToolChain( jdks.getToolchains().get( 0 ), null );
            DefaultToolchain tc2 = new DefaultJavaToolChain( jdksExtra.getToolchains().get( 0 ), null );

            assertTrue( tc1.equals( tc1 ) );
            assertFalse( tc1.equals( tc2 ) );
            assertFalse( tc2.equals( tc1 ) );
            assertTrue( tc2.equals( tc2 ) );
        }
    }
